package com.jerry.zkconfigutil;

import com.jerry.zkconfigutil.annotation.FieldZkConfigurable;
import com.jerry.zkconfigutil.annotation.TypeZkConfigurable;

/**
 * just test
 * 
 * @author JERRY
 * 
 */
@TypeZkConfigurable(path = "/test/jerry", server = "localhost:2181", useOwnZkServer = false)
public final class Demo {

	@FieldZkConfigurable(dynamicUpdate = true)
	public static String F1 = "F1";

	@FieldZkConfigurable(dynamicUpdate = true)
	public static String F2 = "F2";

	@FieldZkConfigurable(dynamicUpdate = true)
	public static Boolean F3 = false;

	@FieldZkConfigurable(dynamicUpdate = true)
	public static Boolean F4 = true;
}
