package br.com.kaikeventura.bdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "big_debug")
public class BigDebug extends AbstractModel implements Serializable {

    private static final long serialVersionUID = -3666687085902772777L;

    @JsonProperty("name")
    @Field("name")
    private String name;

    @JsonProperty("big_developer_brazil")
    @Field("big_developer_brazil")
    private String bigDeveloperBrazil;

    @JsonProperty("technologies_big_debug")
    @Field("technologies_big_debug")
    private List<TechnologyBigDebug> technologiesBigDebug;

    @JsonProperty("visible")
    @Field(name = "visible")
    private Boolean visible;

    @JsonProperty("active")
    @Field(name = "active")
    private Boolean active;
}
