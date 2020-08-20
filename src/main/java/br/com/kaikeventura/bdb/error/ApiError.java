package br.com.kaikeventura.bdb.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ApiError implements Serializable {

    private static final long serialVersionUID = -5857395833275594267L;

    @JsonProperty("error_code")
    private final String errorCode;

    @JsonProperty("error_message")
    private final String errorMessage;

}
