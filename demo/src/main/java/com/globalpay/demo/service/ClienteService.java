package com.globalpay.demo.service;

import java.util.Optional;

import com.globalpay.demo.model.Cliente;
import com.globalpay.demo.service.dto.AltaClienteDto;
import com.globalpay.demo.service.dto.ClienteDto;

public interface ClienteService {

	Cliente createCliente (AltaClienteDto cliente);
	Optional<ClienteDto> findByDni (String dni);
}
