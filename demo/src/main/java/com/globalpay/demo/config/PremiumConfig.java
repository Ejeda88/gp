package com.globalpay.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties(prefix = "premium")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PremiumConfig {
		
	private float precio;
	
	@Value("#{${premium.precio} * 0.2}")
	private float extra;
	
	private int puntos;

}
