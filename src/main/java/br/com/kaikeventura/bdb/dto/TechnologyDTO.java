package br.com.kaikeventura.bdb.dto;

import br.com.kaikeventura.bdb.aux.TechnologyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDTO {

    @NotNull(message = "ERROR-11")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "ERROR-12")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("release_date_of")
    private LocalDate releaseDateOf;

    @NotNull(message = "ERROR-13")
    @JsonProperty("technology_type")
    private TechnologyType technologyType;
}
