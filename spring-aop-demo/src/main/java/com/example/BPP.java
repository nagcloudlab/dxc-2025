package com.example;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


@Component
public class BPP implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("BeforeInitialization : " + beanName);
        // proxies are created here
        // wrap the bean in a proxy
        // return the proxy
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("AfterInitialization : " + beanName);
        return bean;
    }
}
