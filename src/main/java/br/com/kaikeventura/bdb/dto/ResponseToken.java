package br.com.kaikeventura.bdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseToken implements Serializable {

    private static final long serialVersionUID = -8068530153378398538L;

    @JsonProperty("token")
    private String token;
}
