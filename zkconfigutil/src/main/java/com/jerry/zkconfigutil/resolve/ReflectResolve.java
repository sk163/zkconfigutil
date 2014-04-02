package com.jerry.zkconfigutil.resolve;

import java.lang.reflect.Field;

public final class ReflectResolve extends AbstractResolve{

	private final Class<?> cla;
	private final Field field;
	public ReflectResolve(Class<?> cla, Field field){
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
		try {
			field.set(cla, field.getType().cast(src));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
