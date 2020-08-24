package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class TechnologyNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -2855650299625518011L;

    @Getter
    private final String errorCode = "ERROR-20";
}
