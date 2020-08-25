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
public class TechnologyBigDebug implements Serializable {

    private static final long serialVersionUID = 106963977031257820L;

    @JsonProperty("id")
    @Field("id")
    private int id;

    @JsonProperty("name")
    @Field("name")
    private String name;
}
