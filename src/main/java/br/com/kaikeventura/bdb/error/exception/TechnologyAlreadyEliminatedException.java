package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class TechnologyAlreadyEliminatedException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8750952680288464741L;

    @Getter
    private final String errorCode = "ERROR-28";
}
