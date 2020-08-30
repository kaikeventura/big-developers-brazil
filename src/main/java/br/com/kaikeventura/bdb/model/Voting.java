package br.com.kaikeventura.bdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Voting extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 4956493574450800328L;

    @JsonProperty("big_developer_brazil")
    @Field("big_developer_brazil")
    private String bigDeveloperBrazil;

    @JsonProperty("big_debug")
    @Field("big_debug")
    private String bigDebug;

    @JsonProperty("user_email")
    @Field("user_email")
    private String userEmail;

    @JsonProperty("technology_big_debug_id")
    @Field("technology_big_debug_id")
    private int technologyBigDebugId;
}
