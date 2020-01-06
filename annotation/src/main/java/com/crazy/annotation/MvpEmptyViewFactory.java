package com.crazy.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Retention(CLASS)  //编译时注解
@Target(TYPE)
public @interface MvpEmptyViewFactory {

}
