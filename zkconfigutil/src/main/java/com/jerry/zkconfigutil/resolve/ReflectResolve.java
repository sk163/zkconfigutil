package com.jerry.zkconfigutil.resolve;

import java.lang.reflect.Field;

import com.jerry.zkconfigutil.visual.VisualType;

public final class ReflectResolve extends AbstractResolve {

	private final Class<?> cla;
	private final Field field;

	public ReflectResolve(Class<?> cla, Field field) {
		this.cla = cla;
		this.field = field;
	}

	@Override
	public String resolve() {
		try {
			return field.get(cla).toString();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void dResolve(String src) {
		logger.debug("field type = " + field.getType().getSimpleName());
		Object value = null;
		Class<?> type = field.getType();
		if (type == String.class) {
			value = src;
		} else if (type == Boolean.class) {
			value = Boolean.valueOf(src);
		} else if (type == Integer.class) {
			value = Integer.valueOf(src);
		} else if (type == Long.class) {
			value = Long.valueOf(src);
		} else if (type == Double.class) {
			value = Double.valueOf(src);
		} else if (type == Float.class) {
			value = Float.valueOf(src);
		} else if (type == Short.class) {
			value = Short.valueOf(src);
		}else if(type == VisualType.class){
			value = VisualType.valueOf(src);
		}else{
			logger.warn("dosent have this type and return : "+type.getSimpleName());
			return;
		}
		try {
			field.set(cla, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
