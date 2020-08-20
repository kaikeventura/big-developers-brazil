package br.com.kaikeventura.bdb.model;

import br.com.kaikeventura.bdb.aux.TechnologyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Technology {

    @Id
    @JsonIgnore
    @MongoId
    private String id;

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

    @JsonProperty("active")
    @Field(name = "active")
    private Boolean active;

    @JsonProperty("create")
    @Field(name = "create")
    private Instant create;

    @JsonProperty("update")
    @Field(name = "update")
    private Instant update;

    @JsonProperty("disable")
    @Field(name = "disable")
    private Instant disable;
}
