package com.hero.wireless.web.action.interceptor;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateAnnotation {

    // 模块名
    String moduleName() default "";

    // 操作内容
    String option() default "";

}
