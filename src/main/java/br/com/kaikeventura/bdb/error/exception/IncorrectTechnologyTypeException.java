package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class IncorrectTechnologyTypeException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2547986197102961941L;

    @Getter
    private final String errorCode = "ERROR-21";
}
