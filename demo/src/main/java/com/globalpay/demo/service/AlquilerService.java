package com.globalpay.demo.service;

import java.text.ParseException;

import com.globalpay.demo.service.dto.AlquilerDto;
import com.globalpay.demo.service.dto.AlquilerEntregaDto;

public interface AlquilerService {
	AlquilerDto calculoAlquiler(int id_coche, long dias);
	AlquilerDto alquilarCoche(int id_coche,int id_cliente,long dias);
	AlquilerEntregaDto devolucionCoche(int id_coche) throws ParseException;
}
