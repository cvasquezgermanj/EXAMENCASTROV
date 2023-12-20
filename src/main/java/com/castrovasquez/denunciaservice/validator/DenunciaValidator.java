package com.castrovasquez.denunciaservice.validator;

import com.castrovasquez.denunciaservice.entity.Denuncias;
import com.castrovasquez.denunciaservice.exceptions.ValidateServiceExceptions;

public class DenunciaValidator {
	public static void save(Denuncias denuncias){
        if (denuncias.getDni() == null || denuncias.getDni().isEmpty()) {
            throw new ValidateServiceExceptions("El nombre es requerido"); 
        }
        if (denuncias.getDni().length() > 8) {
            throw new ValidateServiceExceptions("El dni es muy largo"); 
        }
        
    }
}
