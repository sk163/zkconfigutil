package com.jerry.zkconfigutil.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jerry.zkconfigutil.resolve.AbstractResolve;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldZkConfigurable {
	String path() default "";

	boolean dynamicUpdate() default false;
	
	Class<? extends AbstractResolve> resove();
	
	@Deprecated()
	String defaultValue() default "";
}