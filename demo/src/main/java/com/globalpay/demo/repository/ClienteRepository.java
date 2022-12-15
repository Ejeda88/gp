package com.globalpay.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globalpay.demo.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query(value = "SELECT * FROM cliente c where c.dni = :dni", nativeQuery = true)
	Optional<Cliente> findByDni(String dni);

}
