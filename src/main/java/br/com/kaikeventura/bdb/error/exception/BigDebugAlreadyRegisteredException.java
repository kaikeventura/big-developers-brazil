package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class BigDebugAlreadyRegisteredException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7540191528792451651L;

    @Getter
    private final String errorCode = "ERROR-26";
}
