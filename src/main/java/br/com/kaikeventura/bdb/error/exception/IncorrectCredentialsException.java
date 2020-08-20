package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class IncorrectCredentialsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -9175537113805676455L;

    @Getter
    private final String errorCode = "ERROR-03";
}