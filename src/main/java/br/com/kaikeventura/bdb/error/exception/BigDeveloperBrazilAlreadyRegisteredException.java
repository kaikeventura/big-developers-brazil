package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class BigDeveloperBrazilAlreadyRegisteredException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -27761243901249797L;

    @Getter
    private final String errorCode = "ERROR-22";
}
