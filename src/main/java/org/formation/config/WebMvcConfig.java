package org.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
        
        registry.addViewController("/").setViewName("redirect:/pages/dashboard");
        
		
//		registry.addViewController("/login").setViewName("login");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);        
	}

	
}
