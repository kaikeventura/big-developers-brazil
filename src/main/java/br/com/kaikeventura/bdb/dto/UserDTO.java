package br.com.kaikeventura.bdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 9207638643907040488L;

    @NotNull(message = "ERROR-04")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "ERROR-05")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "ERROR-06")
    @JsonProperty("date_of_birth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @NotNull(message = "ERROR-07")
    @Email(message = "ERROR-08")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "ERROR-09")
    @Min(value = 3, message = "ERROR-10")
    @JsonProperty("password")
    private String password;

}