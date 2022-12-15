package com.globalpay.demo.rest;

import java.rmi.ServerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalpay.demo.model.Cliente;
import com.globalpay.demo.rest.util.ResponseUtil;
import com.globalpay.demo.service.ClienteService;
import com.globalpay.demo.service.dto.AltaClienteDto;
import com.globalpay.demo.service.dto.ClienteDto;

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	private final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private final ClienteService clientService;

	public ClienteController(ClienteService clientService) {
		this.clientService = clientService;
	}

	@PostMapping("/cliente")
	public ResponseEntity<Cliente> crearCliente(@RequestBody AltaClienteDto altaClienteDto) throws ServerException {
		Cliente cliente = clientService.createCliente(altaClienteDto);

		if (cliente == null) {
			throw new ServerException("Unable to create client");
		} else {
			return new ResponseEntity<>(cliente, HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/cliente/{dni}")
	public ResponseEntity<ClienteDto>findByDni(String dni){
		log.debug("REST request to get client by dni");
		
		return ResponseUtil.wrapOrNotFound(clientService.findByDni(dni));
	}
	
	
}
