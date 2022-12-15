package com.globalpay.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private Integer id;
	
	private String nombre;
	
	private String apellidos;
	
	private String dni;
	
	private Integer points;
	
	//private List<Coche> coches;
}
