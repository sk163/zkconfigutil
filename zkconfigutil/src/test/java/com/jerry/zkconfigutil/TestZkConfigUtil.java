package com.jerry.zkconfigutil;

import com.jerry.zkConfigutil.app.ZkConfigUtil;
import com.jerry.zkConfigutil.exception.NotRegistedException;
import com.jerry.zkconfigutil.demo.Demo;

public final class TestZkConfigUtil {

	public static void main(String[] args) throws InterruptedException,
			InstantiationException, IllegalAccessException,
			NotRegistedException {
		
		ZkConfigUtil app = new ZkConfigUtil("localhost:2181");
		app.register(Demo.class, true);
		
		while (true) {
			loop();
		}
	}

	public static void loop() throws InterruptedException {
		Thread.sleep(2000L);
	}

}
