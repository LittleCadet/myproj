/**
 * 
 */
package com.fuiou.mchnt.entry.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @project entry
 * @description 
 * @author abel.li
 * @creation 2017年3月14日
 * @email 
 * @version
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Check {
	//非空
	boolean require() default false;
	//正则表达式验证
	String regex() default "";
	//正则表达式验证
	int maxlength() default 0;
	int minlength() default 0;
	//中文名
	String zh() default "";
}
