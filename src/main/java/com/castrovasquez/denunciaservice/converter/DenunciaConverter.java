package com.castrovasquez.denunciaservice.converter;

import org.springframework.stereotype.Component;

import com.castrovasquez.denunciaservice.dto.DenunciaDTO;
import com.castrovasquez.denunciaservice.entity.Denuncias;

@Component
public class DenunciaConverter extends AbstractConverter<Denuncias, DenunciaDTO> {

	@Override
	public DenunciaDTO fromEntity(Denuncias entity) {
		if(entity==null) return null;
		return DenunciaDTO.builder()
				.id(entity.getId())
				.dni(entity.getDni())
				.fecha(entity.getFecha())
				.direccion(entity.getDireccion())
				.titulo(entity.getTitulo())
				.descripcion(entity.getDescripcion())
				.build();
	}

	@Override
	public Denuncias fromDTO(DenunciaDTO dto) {
		if(dto == null) { 
			return null;
		}
		return Denuncias.builder()
				.id(dto.getId())
				.dni(dto.getDni())
				.fecha(dto.getFecha())
				.direccion(dto.getDireccion())
				.titulo(dto.getTitulo())
				.descripcion(dto.getDescripcion())
				.build();
		
	}

}
