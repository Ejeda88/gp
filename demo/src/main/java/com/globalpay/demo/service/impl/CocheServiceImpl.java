package com.globalpay.demo.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalpay.demo.repository.CocheRepository;
import com.globalpay.demo.service.CocheService;
import com.globalpay.demo.service.dto.CocheDto;
import com.globalpay.demo.service.impl.util.UtilService;

@Service
public class CocheServiceImpl implements CocheService {
	
	private final Logger log = LoggerFactory.getLogger(CocheServiceImpl.class);

	@Autowired
	private CocheRepository cocheRepository;
	
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public List<CocheDto> findAll() {
		return cocheRepository.findAll().stream().map(car->modelMapper.map(car, CocheDto.class)).collect(Collectors.toList());
	}


	@Override
	public List<CocheDto> findAllAvailable() throws ParseException {
		Date infinito = UtilService.returnInfinito();
		return cocheRepository.findAll().stream().map(car->modelMapper.map(car, CocheDto.class))
				.filter(car -> (car.getFx_alquiler().equals(new Timestamp(infinito.getTime()))))
				.collect(Collectors.toList());
	}


	@Override
	public Optional<CocheDto> findById(int id) {
		return cocheRepository.findById(id)
				.map(car-> new CocheDto(car.getId(),
						car.getMarca(),
						car.getModelo(),
						car.getTipo(),
						car.getFx_alquiler(),
						car.getFx_devolucion(),
						car.getCliente()));		
	}

}
