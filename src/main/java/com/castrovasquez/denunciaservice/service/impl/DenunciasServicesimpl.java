package com.castrovasquez.denunciaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.castrovasquez.denunciaservice.entity.Denuncias;
import com.castrovasquez.denunciaservice.exceptions.GeneralServiceExceptions;
import com.castrovasquez.denunciaservice.exceptions.NoDataFoundException;
import com.castrovasquez.denunciaservice.exceptions.ValidateServiceExceptions;
import com.castrovasquez.denunciaservice.repository.DenunciaRepository;
import com.castrovasquez.denunciaservice.service.DenunciaService;
import com.castrovasquez.denunciaservice.validator.DenunciaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class DenunciasServicesimpl implements DenunciaService {
	
	@Autowired
	private DenunciaRepository denunciaRepository;
	@Override
	@Transactional(readOnly=true)
	public List<Denuncias> findAll(Pageable page) {
	try {
		return denunciaRepository.findAll(page).toList();
	} catch (NoDataFoundException e) {
		log.info(e.getMessage());
		throw e;
	} catch (Exception e) {
		log.error(e.getMessage());
		throw new GeneralServiceExceptions(e.getMessage(),e);
	}
}


	@Override
	@Transactional(readOnly=true)
	public Denuncias findById(int id) {
		try {
			Denuncias denuncias =  denunciaRepository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con el ID"));
			return denuncias;
		} catch (ValidateServiceExceptions | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceExceptions(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Denuncias> findByDni(String dni, Pageable page) {
		try {
			return denunciaRepository.findByDniContaining(dni, page);
		} catch (ValidateServiceExceptions | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceExceptions(e.getMessage(),e);
		}
	}

	@Override
	@Transactional()
	public Denuncias create(Denuncias denuncia) {
		try {
			DenunciaValidator.save(denuncia);
			if(denunciaRepository.findByDni(denuncia.getDni())!=null) {
				throw new ValidateServiceExceptions("Ya existe un registro con ese dni"+denuncia.getDni());
			}
			Denuncias infra = denunciaRepository.save(denuncia);
			return infra;
		} catch (ValidateServiceExceptions | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceExceptions(e.getMessage(),e);
		}
	}

	@Override
	@Transactional()
	public Denuncias update(Denuncias denuncia) {
		try {
			DenunciaValidator.save(denuncia);
			Denuncias infraA = denunciaRepository.findById(denuncia.getId()).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			Denuncias infraB = denunciaRepository.findByDni(denuncia.getDni());
			if(infraB !=null && infraB.getId() != infraA.getId()) {
				throw new ValidateServiceExceptions("Ya existe un registro con el dni"+denuncia.getDni());
			}
			infraA.setDni(denuncia.getDni());
			infraA.setFecha(denuncia.getFecha());
			infraA.setDireccion(denuncia.getDireccion());
			infraA.setTitulo(denuncia.getTitulo());
			infraA.setDescripcion(denuncia.getDescripcion());
			denunciaRepository.save(denuncia);
			
			return infraA;
		} catch (ValidateServiceExceptions | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceExceptions(e.getMessage(),e);
		}
	}

	@Override
	@Transactional()
	public void delete(int id) {
		try {
			Denuncias denuncias = denunciaRepository.findById(id).orElseThrow(()->new NoDataFoundException("No exist el registro con ese ID"));
			denunciaRepository.delete(denuncias);
					
		} catch (ValidateServiceExceptions | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceExceptions(e.getMessage(),e);
		}
		
	}
	


}
