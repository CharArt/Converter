package com.charartpav.converte.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*@author Artem Charykov*/

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/registration").setViewName("registration");
		registry.addViewController("/history").setViewName("history");
		registry.addViewController("/converter").setViewName("converter");
	}
}