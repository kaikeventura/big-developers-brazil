package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class InvalidBigDeveloperBrazilException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2160743813290156914L;

    @Getter
    private final String errorCode = "ERROR-27";
}
