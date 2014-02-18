package com.jerry.zkconfigutil;

import com.jerry.zkconfigutil.DemoResolve.DemoF1Resolve;
import com.jerry.zkconfigutil.DemoResolve.DemoF2Resolve;
import com.jerry.zkconfigutil.DemoResolve.DemoF3Resolve;
import com.jerry.zkconfigutil.annotation.FieldZkConfigurable;
import com.jerry.zkconfigutil.annotation.TypeZkConfigurable;

/**
 * just test
 * @author JERRY
 *
 */
@TypeZkConfigurable(path="/test/jerry", server="localhost:2181", useOwnZkServer=false)
public final class Demo {
	
	@FieldZkConfigurable(resove=DemoF1Resolve.class, dynamicUpdate=true)
	public static String F1 = "F1";
	
	@FieldZkConfigurable(resove=DemoF2Resolve.class, dynamicUpdate=true)
	public static String F2 = "F2";
	
	@FieldZkConfigurable(resove=DemoF3Resolve.class, dynamicUpdate=true)
	public static Boolean F3 = false;
}
