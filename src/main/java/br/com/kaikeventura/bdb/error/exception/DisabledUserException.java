package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class DisabledUserException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5503210541988829107L;

    @Getter
    private final String errorCode = "ERROR-33";
}
