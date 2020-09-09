package br.com.kaikeventura.bdb.model;

import br.com.kaikeventura.bdb.aux.TechnologyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Technology extends AbstractModel implements Serializable {

    private static final long serialVersionUID = -4277863826133217427L;

    @JsonProperty("name")
    @Field(name = "name")
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("release_date_of")
    @Field(name = "release_date_of")
    private LocalDate releaseDateOf;

    @JsonProperty("technology_type")
    @Field(name = "technology_type")
    private TechnologyType technologyType;

    @JsonProperty("photo_id")
    @Field(name = "photo_id")
    private String photoId;

    @JsonProperty("active")
    @Field(name = "active")
    private Boolean active;
}
