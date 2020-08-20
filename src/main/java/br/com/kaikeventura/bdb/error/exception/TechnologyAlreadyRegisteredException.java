package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class TechnologyAlreadyRegisteredException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8692604284534587404L;

    @Getter
    private final String errorCode = "ERROR-15";
}
