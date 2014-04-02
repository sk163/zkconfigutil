package com.jerry.zkconfigutil;

import com.jerry.zkconfigutil.annotation.FieldZkConfigurable;
import com.jerry.zkconfigutil.annotation.TypeZkConfigurable;
import com.jerry.zkconfigutil.resolve.AbstractResolve;

/**
 * just test
 * @author JERRY
 *
 */
@TypeZkConfigurable(path="/test/jerry", server="localhost:2181", useOwnZkServer=false)
public final class Demo {
	
	@FieldZkConfigurable(resolve=DemoF1Resolve.class, dynamicUpdate=true)
	public static String F1 = "F1";
	
	@FieldZkConfigurable(resolve=DemoF2Resolve.class, dynamicUpdate=true)
	public static String F2 = "F2";
	
	@FieldZkConfigurable(resolve=DemoF3Resolve.class, dynamicUpdate=true)
	public static Boolean F3 = false;
	
	
	
	

	public static final class DemoF2Resolve extends AbstractResolve {

		@Override
		public String resolve() {
			// TODO Auto-generated method stub
			return Demo.F2.toString();
		}

		@Override
		public void dResolve(String src) {
			// TODO Auto-generated method stub
			Demo.F2 = Demo.F2.getClass().cast(src);
		}

	}


	

	public static final class DemoF3Resolve extends AbstractResolve {

		@Override
		public String resolve() {
			// TODO Auto-generated method stub
			return Demo.F3.toString();
		}

		@Override
		public void dResolve(String src) {
			// TODO Auto-generated method stub
			Demo.F3 = Demo.F3.getClass().cast(src);
		}

	}



	public static final class DemoF1Resolve extends AbstractResolve {

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
