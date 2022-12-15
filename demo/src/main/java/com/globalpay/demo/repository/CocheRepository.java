package com.globalpay.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globalpay.demo.model.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Integer> {
	Optional<Coche>findById(int id);
}

