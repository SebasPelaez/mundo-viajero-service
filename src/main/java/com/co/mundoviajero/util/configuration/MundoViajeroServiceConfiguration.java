package com.co.mundoviajero.util.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@EnableAsync
@Configuration
@ComponentScan(basePackages = "com.co.mundoviajero")
public class MundoViajeroServiceConfiguration implements WebMvcConfigurer {
	
	@Bean
	@Primary
    public MessageSourceAccessor messageSourceAccessor() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("ISO-8859-1");
        return new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityHandlerInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/login", "/person", "/profile/**", "/state/**");
    }
}
