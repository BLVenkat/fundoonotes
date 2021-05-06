package com.bridgelabz.fundoonotes.configuration;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

	private static MessageSourceAccessor messageSourceAccessor;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@PostConstruct
	private void initMessageSourceAccessor() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/errormessages", "classpath:messages/successmessages");
		messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());

	}

	public static MessageSourceAccessor getMessageAccessor() {
		return messageSourceAccessor;
	}
	
	



}
