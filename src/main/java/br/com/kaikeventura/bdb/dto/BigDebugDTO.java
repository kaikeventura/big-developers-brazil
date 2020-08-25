package br.com.kaikeventura.bdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigDebugDTO implements Serializable {

    private static final long serialVersionUID = 3853633429556935753L;

    @NotNull(message = "ERROR-23")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "ERROR-24")
    @JsonProperty("big_developer_brazil")
    private String bigDeveloperBrazil;

    @NotNull(message = "ERROR-25")
    @JsonProperty("technologies")
    private List<String> technologies;
}
