package org.Server.information_processing.objectfactory.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    /**
     * 请求路径
     * @return
     */
    String path() default "";

    /**
     * 请求方式 默认为GET
     * @return
     */
    RequestMethod method() default RequestMethod.GET;
}
