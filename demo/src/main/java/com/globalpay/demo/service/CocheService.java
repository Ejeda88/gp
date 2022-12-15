package com.globalpay.demo.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.globalpay.demo.service.dto.CocheDto;


public interface CocheService {

	List<CocheDto> findAll();
	List<CocheDto> findAllAvailable() throws ParseException;
	Optional<CocheDto> findById(int id);
}
