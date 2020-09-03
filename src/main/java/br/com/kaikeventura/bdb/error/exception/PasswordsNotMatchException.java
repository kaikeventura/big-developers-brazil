package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class PasswordsNotMatchException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5923832087269701398L;

    @Getter
    private final String errorCode = "ERROR-34";
}
