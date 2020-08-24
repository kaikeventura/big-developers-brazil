package br.com.kaikeventura.bdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class TechnologyState implements Serializable {

    private static final long serialVersionUID = -7651911908411641839L;

    @JsonProperty("technology_name")
    @Field("technology_name")
    private String technologyName;

    @JsonProperty("in_the_game")
    @Field("in_the_game")
    private Boolean inTheGame;
}
