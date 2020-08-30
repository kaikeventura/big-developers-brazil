package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class BigDebugNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -3548952675226298574L;

    @Getter
    private final String errorCode = "ERROR-30";
}
