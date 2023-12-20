package com.castrovasquez.denunciaservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.castrovasquez.denunciaservice.entity.Denuncias;

public interface DenunciaService {
	public List<Denuncias> findAll(Pageable page);
	public Denuncias findById(int id);
	public List<Denuncias> findByDni(String dni, Pageable page);
    public Denuncias create(Denuncias denuncia);
    public Denuncias update(Denuncias denuncia);
    public void delete(int id);
}
