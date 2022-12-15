package com.globalpay.demo.rest;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalpay.demo.service.AlquilerService;
import com.globalpay.demo.service.dto.AlquilerDto;
import com.globalpay.demo.service.dto.AlquilerEntregaDto;

@RestController
@RequestMapping("/api")
public class AlquilerController {
	private final Logger log = LoggerFactory.getLogger(AlquilerController.class);

	@Autowired
	private final AlquilerService alquilerService;
	
	public AlquilerController(AlquilerService alquilerService) {
		this.alquilerService = alquilerService;
	}
	
	@GetMapping("/alquiler_calc/{id_coche}{dias}")
	public ResponseEntity<AlquilerDto>calculoAlquilerCoche(int id_coche, long dias){
		log.debug("Rent calculation to car_id {} during {} days",id_coche,dias);
		return new ResponseEntity<>(alquilerService.calculoAlquiler(id_coche, dias),HttpStatus.OK);
	}
	
	@GetMapping("/entregar/{id_coche}") public
	ResponseEntity<AlquilerEntregaDto>entregarCoche(int id_coche) throws ParseException{ 
		log.debug("Car return id {}",id_coche);
		return new ResponseEntity<>(alquilerService.devolucionCoche(id_coche),HttpStatus.OK); 
	}
	
	@PutMapping("/alquilar/{id_coche}{id_cliente}{dias}")
	public ResponseEntity<AlquilerDto>AlquilerCoche(int id_coche, int id_cliente, long dias){
		log.debug("Rental of car {} by client {} for {} days",id_coche,id_cliente, dias);
		return new ResponseEntity<>(alquilerService.alquilarCoche(id_coche, id_cliente ,dias),HttpStatus.OK);
	}	
	
	
	 
}
