package com.factory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/home-page").setViewName("home-page");
        registry.addViewController("/admin-home-page").setViewName("admin-home-page");
        registry.addViewController("/list-categories-user").setViewName("list-categories-user");
        registry.addViewController("/list-books-user").setViewName("list-books-user");
    }
}
