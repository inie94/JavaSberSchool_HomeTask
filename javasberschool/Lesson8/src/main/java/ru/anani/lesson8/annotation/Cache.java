package ru.anani.lesson8.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Указывает, что наша Аннотация может быть использована
//Во время выполнения через Reflection (нам как раз это нужно).
@Retention(RetentionPolicy.RUNTIME)

//Указывает, что целью нашей Аннотации является метод
//Не класс, не переменная, не поле, а именно метод.
@Target(ElementType.METHOD)
public @interface Cache {
    CachedType cacheType();
    String fileNamePrefix() default "";
    boolean zip() default false;
    long listList() default 16;
    Class[] identityBy() default {};
}
