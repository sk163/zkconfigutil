package com.jerry.zkconfigutil.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jerry.zkconfigutil.resolve.Resolve;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldZkConfigurable {
	String path() default "";

	boolean dynamicUpdate() default false;
	
	Class<? extends Resolve> resove();
	
	@Deprecated()
	String defaultValue() default "";
}