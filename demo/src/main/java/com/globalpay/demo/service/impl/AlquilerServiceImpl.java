package com.globalpay.demo.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalpay.demo.config.Constants;
import com.globalpay.demo.config.PequenoConfig;
import com.globalpay.demo.config.PremiumConfig;
import com.globalpay.demo.config.SuvConfig;
import com.globalpay.demo.model.Cliente;
import com.globalpay.demo.model.Coche;
import com.globalpay.demo.repository.ClienteRepository;
import com.globalpay.demo.repository.CocheRepository;
import com.globalpay.demo.service.AlquilerService;
import com.globalpay.demo.service.dto.AlquilerDto;
import com.globalpay.demo.service.dto.AlquilerEntregaDto;
import com.globalpay.demo.service.impl.util.UtilService;

@Service
public class AlquilerServiceImpl implements AlquilerService {

	@Autowired
	private final CocheRepository cocheRepository;
	
	@Autowired
	private final ClienteRepository clienteRepository;
	
	@Autowired
	private final PequenoConfig pequenoConfig;
	
	@Autowired
	private final SuvConfig suvConfig;
	
	@Autowired
	private final PremiumConfig premiumConfig;
		
	public AlquilerServiceImpl(CocheRepository cocheRepository, PequenoConfig pequenoConfig, SuvConfig suvConfig, PremiumConfig premiumConfig, ClienteRepository clienteRepository) {
		this.cocheRepository = cocheRepository;
		this.clienteRepository = clienteRepository;
		this.pequenoConfig = pequenoConfig;
		this.suvConfig = suvConfig;
		this.premiumConfig = premiumConfig;
	}
	
	
	@Override
	public AlquilerDto calculoAlquiler(int id_coche, long dias) {
		AlquilerDto alquiler = new AlquilerDto();
		Optional<Coche> coche = cocheRepository.findById(id_coche);
		
		if(coche.isPresent()) {
			alquiler.setCoche(coche.get());
			
			switch (coche.get().getTipo()) {
			case Constants.TYPE_PEQUEÑO:
				alquiler.setAlquiler(calculoPequeño(dias));
				break;
			case Constants.TYPE_SUV:
				alquiler.setAlquiler(calculoSuv(dias));
				break;

			default:
				alquiler.setAlquiler(calculoPremium(dias));
				break;
			}
		}
		return alquiler;
	}
	
	@Override
	public AlquilerEntregaDto devolucionCoche(int id_coche) throws ParseException {
		
		AlquilerEntregaDto entrega = new AlquilerEntregaDto();
		Optional<Coche> coche = cocheRepository.findById(id_coche);
		Coche toSave = new Coche();
		long dias;
		float alquiler;
		
		//Si existe el coche		
		if(coche.isPresent()) {
			int cliente_id = coche.get().getCliente().getId();
			Date fx_entrega = coche.get().getFx_devolucion();
			Date fx_alquiler = coche.get().getFx_alquiler();
			Date today = new Date(System.currentTimeMillis());
			
			toSave=coche.get();
			
			//Ponemos las fechas a infinito y el cliente 0 (simular la devolución)
			toSave.setFx_alquiler(UtilService.returnInfinito());
			toSave.setFx_devolucion(UtilService.returnInfinito());
			toSave.setCliente(defaultCliente());
			
			
			//Guardamos el coche actualizado
			entrega.setCoche(cocheRepository.save(toSave));
			
			//Sumamos los puntos de fidelidad 
			Optional<Cliente> cliente = clienteRepository.findById(cliente_id);
			if(cliente.isPresent()) {
				Cliente cli = cliente.get();
				cli = sumaPuntos(cli, coche.get().getTipo());
				clienteRepository.save(cli);
			}
			
			//Entrega tardia
			if(today.after(fx_entrega)) {
				entrega.setExtra(calculoExtra(fx_entrega, coche.get().getTipo()));
			}
			dias = getDifferenceDays(fx_alquiler, fx_entrega);
			
			//Calculo del alquiler
			alquiler = calculoAlquiler(id_coche, dias).getAlquiler();
			entrega.setAlquiler(alquiler);
			
			//Total
			entrega.setTotal(alquiler+entrega.getExtra());
			
		}
		return entrega;
	}
	
	@Override
	public AlquilerDto alquilarCoche(int id_coche, int id_cliente, long dias) {
		//Hacemos el calculo del alquiler del coche
		AlquilerDto alquilerDto = calculoAlquiler(id_coche, dias);
		
		//Asociamos el coche al cliente
		Optional<Cliente> cli = clienteRepository.findById(id_cliente);
		if(cli.isPresent()) {
			Optional<Coche>coche = cocheRepository.findById(id_coche);
			if(coche.isPresent()) {
				Coche carToSave = coche.get();
				carToSave.setCliente(cli.get());
				cocheRepository.save(carToSave);
			}
		}
		
		return alquilerDto;
	}
	
	/**
	 * Calcula el extra por tipo de coche si la fecha de entrega se a excedido
	 * @param entrega
	 * @param tipo
	 * @return el extra
	 */
	private float calculoExtra(Date entrega, String tipo) {
		long dias;
		float extra;
		
		//Calculamos el numero de dias de más
		dias = getDifferenceDays(entrega, new Date(System.currentTimeMillis()));
		
		switch (tipo) {
		case Constants.TYPE_PEQUEÑO:
			extra = pequenoConfig.getExtra()*dias;
			break;
		case Constants.TYPE_PREMIUM:
			extra = premiumConfig.getExtra()*dias;
			break;
		default:
			extra = suvConfig.getExtra()*dias;
			break;
		}
		
		return extra;
		
	}
	
	/**
	 * Suma los puntos de fidelidad al cliente según el tipo de coche.
	 * @param cliente
	 * @param tipo_coche
	 * @return el cliente con los puntos de fidelidad actualizados
	 */
	private Cliente sumaPuntos(Cliente cliente, String tipo_coche) {
		int total; 
		
		switch (tipo_coche) {
		case Constants.TYPE_PEQUEÑO:
			total = cliente.getPoints() + Constants.POINT_PEQUEÑO;
			break;
		case Constants.TYPE_PREMIUM:
			total = cliente.getPoints() + Constants.POINT_PREMIUM;
			break;
		default:
			total = cliente.getPoints() + Constants.POINT_SUV;
			break;
		}
		
		cliente.setPoints(total);
		
		return cliente;
	}

	/**
	 * 
	 * @param dias
	 * @return
	 */
	private float calculoPequeño(long dias) {
		float precio=0;
		float auxPrecio=0;
		//Los 7 primeros días: precio pequeño por día. 
		if(dias<=7) {
			precio = pequenoConfig.getPrecio()*dias;
		//Más de 7 dias 60% precio pequeño por dia
		}else {
			auxPrecio = (dias-7)*(pequenoConfig.getPrecio()*0.6F);
			precio = (pequenoConfig.getPrecio()*7)+auxPrecio;
		}
		return precio;
	}
	
	/**
	 * 
	 * @param dias
	 * @return
	 */
	private float calculoPremium(long dias) {
		return premiumConfig.getPrecio()*dias;
		
	}
	
	/**
	 * 
	 * @param dias
	 * @return
	 */
	private float calculoSuv(long dias) {
		float precio = 0;
				
		//TRAMO 1, Si dias <= 7 --> dias * precio
		if(dias<=7) {
			precio = suvConfig.getPrecio()*dias;
		}
		//TRAMOS 3,Si son mas de 30 dias --> (7*precio)+(23*precio*0.8)+((dias-30)*precio*0.5)
		else if(dias>30){
			precio = (7*suvConfig.getPrecio())+(23*suvConfig.getPrecio()*0.8F)+((dias-30)*suvConfig.getPrecio()*0.5F);
		}
		//TRAMO 2, si dias [8,30] --> ((dias - 7)*precio*0.8F)+(7*precio)
		else {
			precio = (7*suvConfig.getPrecio())+((dias-7)*suvConfig.getPrecio()*0.8F);
		}
		
		return precio;
		
	}
	
	/**
	 * Devuelve la diferencia de dias entre dos fechas.
	 * @param d1, fx_ini
	 * @param d2, fx_end
	 * @return
	 */
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	private float calculaExtraPequeno(Date fx_fin) {
		long extra = 0;
		extra = getDifferenceDays(fx_fin, new Date(System.currentTimeMillis()));
		return extra*pequenoConfig.getExtra();
	}
	
	private float calculaExtraSuv(Date fx_fin) {
		
		long extra = 0;
		extra = getDifferenceDays(fx_fin, new Date(System.currentTimeMillis()));
		return extra*suvConfig.getExtra();
	}

	private float calculaExtraPremium(Date fx_fin) {
		long extra = 0;
		extra = getDifferenceDays(fx_fin, new Date(System.currentTimeMillis()));
		return extra*premiumConfig.getExtra();
	}	
	
	private Cliente defaultCliente() {
		return clienteRepository.findById(0).get();
	}


	
}
