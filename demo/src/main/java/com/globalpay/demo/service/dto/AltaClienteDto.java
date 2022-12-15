package com.globalpay.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AltaClienteDto {

    private String nombre;
	
	private String apellidos;
	
	private String dni;
	
	@JsonIgnore
	private Integer points;
}
