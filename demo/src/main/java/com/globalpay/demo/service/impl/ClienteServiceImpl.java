package com.globalpay.demo.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalpay.demo.model.Cliente;
import com.globalpay.demo.repository.ClienteRepository;
import com.globalpay.demo.service.ClienteService;
import com.globalpay.demo.service.dto.AltaClienteDto;
import com.globalpay.demo.service.dto.ClienteDto;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Cliente createCliente(AltaClienteDto clienteDto) {
		clienteDto.setPoints(0);
		return clienteRepository.save(modelMapper.map(clienteDto, Cliente.class));
	}

	@Override
	public Optional<ClienteDto> findByDni(String dni) {
		
		return clienteRepository.findByDni(dni)
				.map(cli-> new ClienteDto(cli.getId(),
						cli.getNombre(),
						cli.getApellidos(),
						cli.getDni(),
						cli.getPoints()));
	}
	
	

}
