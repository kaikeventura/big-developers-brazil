package br.com.kaikeventura.bdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotingDTO implements Serializable {

    private static final long serialVersionUID = -7059340999222857161L;

    @JsonProperty("technology_big_debug_id")
    private int technologyBigDebugId;
}
