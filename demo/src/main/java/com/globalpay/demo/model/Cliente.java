package com.globalpay.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
public class Cliente implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "myDbSeq",
    initialValue = 2, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "mySeqGen")
    private Integer id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "apellidos", nullable = false)
	private String apellidos;
	
	@Column(name = "dni", nullable = false, unique = true)
	private String dni;
	
	@Column(name = "points")
	private Integer points;
	
	/*@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinColumn(name = "clienteId", referencedColumnName = "id")
	private List<Coche> coches;
	*/
	

}
