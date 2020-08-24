package br.com.kaikeventura.bdb.model;

import br.com.kaikeventura.bdb.aux.TechnologyType;
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
@Document(collection = "big_developer_brazil")
public class BigDeveloperBrazil extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 8787072863661914415L;

    @JsonProperty("name")
    @Field("name")
    private String name;

    @JsonProperty("technology_type")
    @Field("technology_type")
    private TechnologyType technologyType;

    @JsonProperty("technologies")
    @Field("technologies")
    private List<TechnologyState> technologies;

    @JsonProperty("active")
    @Field(name = "active")
    private Boolean active;
}