package br.com.kaikeventura.bdb.util;

import br.com.kaikeventura.bdb.dto.TechnologyDTO;
import br.com.kaikeventura.bdb.model.Technology;
import org.springframework.stereotype.Component;

@Component
public class TechnologyUtil {

    public Technology toNewTechnology(TechnologyDTO technologyDTO) {
        return new Technology(
                technologyDTO.getName(),
                technologyDTO.getReleaseDateOf(),
                technologyDTO.getTechnologyType(),
                true
        );
    }
}
