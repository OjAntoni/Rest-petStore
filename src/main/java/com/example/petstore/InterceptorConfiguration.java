package com.example.petstore;

import com.example.petstore.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    UserInterceptor userInterceptor() {
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/createWithArray")
                .excludePathPatterns("/user/createWithList");
    }
}
