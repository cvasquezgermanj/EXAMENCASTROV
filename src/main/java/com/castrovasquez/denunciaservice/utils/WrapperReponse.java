package com.castrovasquez.denunciaservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class WrapperReponse <T>{
    private boolean ok;
    private String message;
    private T body;

    public ResponseEntity<WrapperReponse<T>> createResponse() {
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    public ResponseEntity<WrapperReponse<T>> createResponse(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

}
