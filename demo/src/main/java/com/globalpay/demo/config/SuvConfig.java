package com.globalpay.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties(prefix = "suv")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuvConfig {

	private float precio;
	
	@Value("#{${pequeno.precio} * 0.6}")
	private float extra;
	
	private int puntos;
	
}
