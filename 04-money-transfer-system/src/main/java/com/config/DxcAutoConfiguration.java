package com.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
public class DxcAutoConfiguration {

    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }

}
