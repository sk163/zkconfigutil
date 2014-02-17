package com.jerry.zkConfigutil.app;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.log4j.Logger;

import com.jerry.zkConfigutil.annotation.FieldZkConfigurable;
import com.jerry.zkConfigutil.annotation.TypeZkConfigurable;
import com.jerry.zkConfigutil.exception.NotRegistedException;
import com.jerry.zkConfigutil.zkserializer.StringZkSerializer;
import com.jerry.zkconfigutil.resolve.Resolve;
import com.jerry.zkconfigutil.util.Updater;

public final class ZkConfigUtil implements IZkDataListener {

	private final Logger logger = Logger.getLogger(ZkConfigUtil.class);
	private final String globalZkServer;

	public ZkConfigUtil(String globalZkServer) {
		this.globalZkServer = globalZkServer;
	}

	public final void register(Class<?> cla, boolean isCreateIfNUll)
			throws NotRegistedException, InstantiationException,
			IllegalAccessException {
		TypeZkConfigurable typeZkConfigurable = cla
				.getAnnotation(TypeZkConfigurable.class);
		if (typeZkConfigurable == null) {
			throw new NotRegistedException();
		}

		boolean useOwnZkServer = typeZkConfigurable.useOwnZkServer();

		final ZkClient zkClient;
		if (useOwnZkServer) {
			final String server = typeZkConfigurable.server();
			if ("".equals(server)) {
				logger.error("please set zkServer or set typeZkConfigurable.useOwnZkServer()=true to use globalZkServer system will exit!!!");
				System.exit(0);
			}
			zkClient = this.makeZkClient(server);
		} else {
			if ("".equals(this.globalZkServer)) {
				logger.error("please set globalZkServer or set typeZkConfigurable.useOwnZkServer()=false to use own zkserver system will exit!!!");
				System.exit(0);
			}
			zkClient = this.makeZkClient(this.globalZkServer);
		}

		final String path = this.makeZkPath(typeZkConfigurable.path(),
				cla.getSimpleName());

		final Field[] fields = cla.getDeclaredFields();

		for (Field field : fields) {
			logger.debug("field : " + field.getName() + "   type : "
					+ field.getType().getSimpleName());
			FieldZkConfigurable fieldZkConfigurable = field
					.getAnnotation(FieldZkConfigurable.class);
			if (fieldZkConfigurable == null)
				continue;

			final String fieldPath;
			if (fieldZkConfigurable.path().equals("")) {
				fieldPath = this.makeZkPath(path, field.getName());
			} else {
				fieldPath = this.makeZkPath(path, fieldZkConfigurable.path());
			}

			String value = zkClient.readData(fieldPath, true);
			logger.debug(fieldPath + " : " + value);

			Class<? extends Resolve> resolve = fieldZkConfigurable.resove();

			Resolve resolveInstance = resolve.newInstance();

			/**
			 * Dosen't have value
			 */
			if (value == null && !isCreateIfNUll) {
				continue;
			} else if (value == null && isCreateIfNUll) {
				zkClient.createPersistent(fieldPath, true);
				String defaultValue = resolveInstance.resolve();
				zkClient.writeData(fieldPath, defaultValue);
			} else {
				/**
				 * have value
				 */
				resolveInstance.dResolve(value);
			}

			if (fieldZkConfigurable.dynamicUpdate()) {
				logger.debug("dynamicUpdate " + fieldPath);
				zkClient.subscribeDataChanges(fieldPath, this);
				Updater.register(fieldPath, resolveInstance);
			}
		}
	}

	private final Map<String, ZkClient> zkClientCache = new HashMap<String, ZkClient>(
			4);

	private final ZkClient makeZkClient(String server) {

		if (zkClientCache.containsKey(server))
			return zkClientCache.get(server);

		final ZkClient zkClient = new ZkClient(server, 30000, 30000,
				new StringZkSerializer());
		zkClientCache.put(server, zkClient);
		return zkClient;
	}

	private final String makeZkPath(String parent, String pathName) {
		final String separator = "/";
		if (!parent.startsWith(separator)) {
			parent = separator + parent;
		}
		if (!parent.endsWith(separator)) {
			parent = parent + separator;
		}
		if (pathName.startsWith(separator)) {
			pathName = pathName.substring(1);
		}
		return parent + pathName;
	}

	@Override
	public void handleDataChange(String dataPath, Object data) throws Exception {
		logger.warn("change event : " + dataPath + " : " + data.toString());
		Updater.update(dataPath, data.toString());
	}

	@Override
	public void handleDataDeleted(String dataPath) throws Exception {
		logger.warn("delete event : " + dataPath);

	}

}
