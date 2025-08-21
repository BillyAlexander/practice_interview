package com.evilla.test.solicitud.configs;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Value("${testex.api.url.timeOutConnect}")
    int TIME_OUT_CONNECT;

    @Value("${testex.api.url.timeOutRead}")
    int TIME_OUT_READ;
    
    @Bean
	public RestTemplate resTemplate(RestTemplateBuilder builder) {
		return builder
				.setConnectTimeout(Duration.ofMillis(TIME_OUT_CONNECT))
				.setReadTimeout(Duration.ofMillis(TIME_OUT_READ))
				.build();
	}
}
