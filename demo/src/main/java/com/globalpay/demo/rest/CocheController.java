package com.globalpay.demo.rest;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalpay.demo.rest.util.ResponseUtil;
import com.globalpay.demo.service.CocheService;
import com.globalpay.demo.service.dto.CocheDto;

@RestController
@RequestMapping("/api")
public class CocheController {

	private final Logger log = LoggerFactory.getLogger(CocheController.class);

	@Autowired
	private final CocheService cocheService;

	public CocheController(CocheService cocheService) {
		this.cocheService = cocheService;
	}

	/**
	 * 
	 * @param pageable @return, la lista de todos los coches (disponibles y no disponibles).
	 */
    @GetMapping("/coches")
    public ResponseEntity<List<CocheDto>> getAllCars() {
    	log.debug("Find All Cars");
        final List<CocheDto> ls = cocheService.findAll();
        
        return new ResponseEntity<>(ls, HttpStatus.OK);
    }
    
    /**
	 * 
	 * @param pageable @return, la lista de todos los coches disponibles.
     * @throws ParseException 
	 */
    @GetMapping("/available")
    public ResponseEntity<List<CocheDto>> getAllAvailableCars() throws ParseException {
    	log.debug("Find All Available Cars");
        final List<CocheDto> ls = cocheService.findAllAvailable();
        
        return new ResponseEntity<>(ls, HttpStatus.OK);
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/coche/{id}")
    public ResponseEntity<CocheDto> getCocheById(int id){
    	log.debug("Find car by id{}",id);
    	return ResponseUtil.wrapOrNotFound(cocheService.findById(id));
    }

}
