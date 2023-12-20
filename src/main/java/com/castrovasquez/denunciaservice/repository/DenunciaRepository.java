package com.castrovasquez.denunciaservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castrovasquez.denunciaservice.entity.Denuncias;

@Repository

public interface DenunciaRepository extends JpaRepository <Denuncias, Integer>{
	List<Denuncias> findByDniContaining(String dni, Pageable page);
	Denuncias findByDni(String dni);
}

