package com.globalpay.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coche")
@Data
@NoArgsConstructor
public class Coche implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "marca", nullable = false)
	private String marca;
	
	@Column(name = "modelo", nullable = false)
	private String modelo;
	
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	
	 @ManyToOne()
	 @JoinColumn(name="cliente_id", nullable = true) 
	 private Cliente cliente;
	 
	
	@Column(name = "fx_alquiler")	
	private Date fx_alquiler;
	
	@Column(name = "fx_devolucion")	
	private Date fx_devolucion;
	
	
	
}
