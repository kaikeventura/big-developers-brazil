package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class ErrorMountingVoteMessageException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -6028512343998437364L;

    @Getter
    private final String errorCode = "ERROR-35";
}
