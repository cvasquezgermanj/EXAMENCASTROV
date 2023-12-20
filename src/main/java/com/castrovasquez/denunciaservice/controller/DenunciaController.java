package com.castrovasquez.denunciaservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castrovasquez.denunciaservice.converter.DenunciaConverter;
import com.castrovasquez.denunciaservice.dto.DenunciaDTO;
import com.castrovasquez.denunciaservice.entity.Denuncias;
import com.castrovasquez.denunciaservice.service.DenunciaService;
import com.castrovasquez.denunciaservice.utils.WrapperReponse;

@RestController
@RequestMapping("/v1/denuncias")

public class DenunciaController {
	@Autowired
    private DenunciaService denunciaService;
    
	@Autowired
	private DenunciaConverter denunciaConverter;
	
    @GetMapping()
    public ResponseEntity<List<DenunciaDTO>> findAll(
            @RequestParam(value = "dni", required = false, defaultValue = "") String dni,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ){
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Denuncias> denuncias;
        
        if(dni == null) {
        	denuncias = denunciaService.findAll(page);
        } else {
        	denuncias = denunciaService.findByDni(dni, page);
        }
        /*
        if(productos.isEmpty()) {
        	return ResponseEntity.noContent().build();
        }*/
        List<DenunciaDTO> DenunciaDTO = denunciaConverter.fromEntity(denuncias);
        return new WrapperReponse(true,"success", DenunciaDTO).createResponse(HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
	public ResponseEntity<WrapperReponse<DenunciaDTO>> findById(@PathVariable("id") int id){
    	Denuncias denuncias = denunciaService.findById(id);
		if (denuncias ==null) {
			return ResponseEntity.notFound().build();
		}
		DenunciaDTO DenunciaDTO=denunciaConverter.fromEntity(denuncias);
		return new WrapperReponse<DenunciaDTO>(true,"success", DenunciaDTO).createResponse(HttpStatus.OK);
	}
    
    @PostMapping
	public ResponseEntity<DenunciaDTO> create(@RequestBody DenunciaDTO DenunciaDTO){
    	Denuncias denuncias = denunciaService.create(denunciaConverter.fromDTO(DenunciaDTO));
    	DenunciaDTO denunciasDTO = denunciaConverter.fromEntity(denuncias);
		return new WrapperReponse(true,"success", denunciasDTO).createResponse(HttpStatus.CREATED);
	}
    
    @PutMapping(value="/{id}")
	public ResponseEntity<DenunciaDTO> update(@PathVariable("id") int id, @RequestBody DenunciaDTO infraccionDTO){
    	Denuncias denuncias = denunciaService.update(denunciaConverter.fromDTO(infraccionDTO));
		if(denuncias == null) {
			return ResponseEntity.notFound().build();
		}
		DenunciaDTO infraccionsDTO = denunciaConverter.fromEntity(denuncias);
		return new WrapperReponse(true,"success", infraccionsDTO).createResponse(HttpStatus.OK);		
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<DenunciaDTO> delete(@PathVariable("id") int id){
		denunciaService.delete(id);
		return new WrapperReponse(true,"success", null).createResponse(HttpStatus.OK);
	}
}
