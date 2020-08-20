package br.com.kaikeventura.bdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = -7328093396709190245L;

    @NotNull(message = "ERROR-07")
    @Email(message = "ERROR-08")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "ERROR-09")
    @JsonProperty("password")
    private String password;
}
