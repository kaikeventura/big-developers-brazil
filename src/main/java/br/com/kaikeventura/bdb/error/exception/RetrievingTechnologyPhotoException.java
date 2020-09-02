package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class RetrievingTechnologyPhotoException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2441089321758233629L;

    @Getter
    private final String errorCode = "ERROR-32";
}
