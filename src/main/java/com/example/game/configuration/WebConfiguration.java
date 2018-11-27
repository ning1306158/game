package com.example.game.configuration;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.game.filter.MyFilter;

@Configuration
public class WebConfiguration {
	
	
	@Bean
	public FilterRegistrationBean<Filter> filterRegistration()
	{
		FilterRegistrationBean<Filter> registration=new FilterRegistrationBean<>();
		registration.setFilter(new MyFilter());
		return registration;
	}

}
