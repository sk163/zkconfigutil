package com.jerry.zkconfigutil.resolve;

import com.jerry.zkconfigutil.demo.Demo;

public final class DemoResolve {
	
	
	public static final class DemoF1Resolve extends AbstractResolve{

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
	
	public static final class DemoF2Resolve extends AbstractResolve{

		@Override
		public String resolve() {
			// TODO Auto-generated method stub
			return Demo.F2;
		}

		@Override
		public void dResolve(String src) {
			// TODO Auto-generated method stub
			Demo.F2 = src;
		}
		
	}
	
	public static final class DemoF3Resolve extends AbstractResolve{

		@Override
		public String resolve() {
			// TODO Auto-generated method stub
			return Demo.F3.toString();
		}

		@Override
		public void dResolve(String src) {
			// TODO Auto-generated method stub
			Demo.F3 = Boolean.parseBoolean(src);
		}
		
	}
}
