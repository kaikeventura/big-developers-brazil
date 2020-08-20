package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class EmailNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5492187491281512896L;

    @Getter
    private final String errorCode = "ERROR-02";
}
