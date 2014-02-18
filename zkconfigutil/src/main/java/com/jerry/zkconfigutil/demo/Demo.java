package com.jerry.zkconfigutil.demo;

import com.jerry.zkconfigutil.annotation.FieldZkConfigurable;
import com.jerry.zkconfigutil.annotation.TypeZkConfigurable;
import com.jerry.zkconfigutil.resolve.DemoResolve.DemoF1Resolve;
import com.jerry.zkconfigutil.resolve.DemoResolve.DemoF2Resolve;
import com.jerry.zkconfigutil.resolve.DemoResolve.DemoF3Resolve;

/**
 * just test
 * @author JERRY
 *
 */
@TypeZkConfigurable(path="/test/jerry", server="localhost:2181", useOwnZkServer=true)
public final class Demo {
	
	@FieldZkConfigurable(resove=DemoF1Resolve.class, dynamicUpdate=false)
	public static String F1 = "F1";
	
	@FieldZkConfigurable(resove=DemoF2Resolve.class, dynamicUpdate=true)
	public static String F2 = "F2";
	
	@FieldZkConfigurable(resove=DemoF3Resolve.class, dynamicUpdate=true)
	public static Boolean F3 = false;
}
