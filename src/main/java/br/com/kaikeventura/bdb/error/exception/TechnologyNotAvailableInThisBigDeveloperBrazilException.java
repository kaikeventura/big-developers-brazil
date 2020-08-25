package br.com.kaikeventura.bdb.error.exception;

import lombok.Getter;

import java.io.Serializable;

public class TechnologyNotAvailableInThisBigDeveloperBrazilException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3213589966455040774L;

    @Getter
    private final String errorCode = "ERROR-29";
}
