package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class BigDebugNotAvailableException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7175988680431010487L;

    @Getter
    private final String errorCode = "ERROR-31";
}
