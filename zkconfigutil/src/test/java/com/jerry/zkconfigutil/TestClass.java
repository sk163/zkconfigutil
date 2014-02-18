package com.jerry.zkconfigutil;

import com.jerry.zkconfigutil.resolve.Resolve;

public final class TestClass {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		Class<? extends Resolve> resolve = TestResolve.class;
		
		Resolve resolveInstance = resolve.newInstance();
		
		resolveInstance = resolve.newInstance();
		resolveInstance = resolve.newInstance();
		resolveInstance = resolve.newInstance();
		
		System.out.println(resolveInstance == resolve.newInstance());
		
		String value = resolveInstance.resolve();
		
		System.out.println(value);
	}
	
	
	public static final class TestResolve implements Resolve{
		
		public TestResolve(){
			System.err.println("TestResolve into ...");
		}
		
		@Override
		public String resolve() {
			// TODO Auto-generated method stub
			return Demo.F1;
		}

		@Override
		public void dResolve(String src) {
			// TODO Auto-generated method stub
			Demo.F1 = src;
		}
		
	}

}
