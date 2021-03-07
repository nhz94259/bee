package com.nhz.mycode.json;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonPathProperty {
    /**
     *  Follow the JsonPath rule to locate the target
     */
    String[] path();
    /**
     *  when the value is empty，return this defaultValue
     */
    String defaultValue() default "";
    /**
     *  the alias of key in map
     */
    String alias() default "";
}
