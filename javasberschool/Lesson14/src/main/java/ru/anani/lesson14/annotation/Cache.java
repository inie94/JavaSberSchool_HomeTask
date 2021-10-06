package ru.anani.lesson14.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CachedType cacheType();
    String fileNamePrefix() default "";
    boolean zip() default false;
    int listList() default 16;
    int[] ignoreIdentificationBy() default {};
}
