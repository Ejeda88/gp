package com.globalpay.demo.service.dto;

import com.globalpay.demo.model.Coche;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerDto {

	private Coche coche;
	
	private float alquiler;
	
	private float extra;
	
}
