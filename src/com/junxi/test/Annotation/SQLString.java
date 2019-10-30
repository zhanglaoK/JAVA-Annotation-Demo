package com.junxi.test.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    //列名
    String name() default "";
    //长度，比如varchar（80）
    int value() default 0;
    //嵌套注解
    Constraints constraint() default @Constraints;
}
