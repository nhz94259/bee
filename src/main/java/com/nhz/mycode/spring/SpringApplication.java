package com.nhz.mycode.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {
    // BeanDefinition bean的定义优先全部获取


    //class扫描-> BeanDefinition-> Map<beanName,BeanDefinition>
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        Object userService = applicationContext.getBean("userService");
    }
}
