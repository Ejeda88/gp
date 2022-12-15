package com.globalpay.demo.service.dto;

import java.util.Date;

import com.globalpay.demo.model.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocheDto {

    private Integer id;
	
	private String marca;
	
	private String modelo;
	
	private String tipo;
	
	private Date fx_alquiler;
	
	private Date fx_devolucion;
	
	private Cliente cliente;
	
	
}
