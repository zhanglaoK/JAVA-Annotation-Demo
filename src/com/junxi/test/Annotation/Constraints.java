package com.junxi.test.Annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {

    //是否为主键
    boolean primaryKey() default false;
    //是否允许为null
    boolean allowNull() default false;
    //是否唯一
    boolean unique() default false;

}
