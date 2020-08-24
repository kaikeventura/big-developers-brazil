package br.com.kaikeventura.bdb.dto;

import br.com.kaikeventura.bdb.aux.TechnologyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BigDeveloperBrazilDTO implements Serializable {

    private static final long serialVersionUID = -5235252468638323157L;

    @NotNull(message = "ERROR-16")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "ERROR-17")
    @JsonProperty("technology_type")
    private TechnologyType technologyType;

    @NotNull(message = "ERROR-18")
    @NotEmpty(message = "ERROR-19")
    @JsonProperty("technologies")
    private List<String> technologies;
}
