package com.jerry.zkconfigutil;

import com.jerry.zkconfigutil.annotation.FieldZkConfigurable;
import com.jerry.zkconfigutil.annotation.TypeZkConfigurable;
import com.jerry.zkconfigutil.visual.VisualType;

/**
 * just test
 * 
 * @author JERRY
 * 
 */
@TypeZkConfigurable(path = "/test/jerry", server = "localhost:2181", useOwnZkServer = false)
public final class Demo {

	@FieldZkConfigurable(resolve = DemoResolve.DemoF1Resolve.class, dynamicUpdate = true)
	public static String F1 = "F1";

	@FieldZkConfigurable(dynamicUpdate = true)
	public static String F2 = "F2";

	@FieldZkConfigurable(dynamicUpdate = true)
	public static Boolean F3 = false;

	@FieldZkConfigurable(dynamicUpdate = true)
	public static Boolean F4 = true;

	@FieldZkConfigurable(dynamicUpdate = true)
	public static TestVisualType testVisualType = new TestVisualType();

	static class TestVisualType extends VisualType {
		String s = "123";

		@Override
		public VisualType valueOf(String src) {
			this.s = src;
			return this;
		}

		@Override
		public String toString() {
			return s;
		}

	}
}
