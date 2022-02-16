package com.bugtracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //
				.allowedOrigins("*") //
				.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "OPTIONS") //
				.allowedHeaders("Content-Type, Authorization, x-requested-with") //
				.maxAge(900);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**") //
				.addResourceLocations("classpath:/static/resources/");
	}
}
