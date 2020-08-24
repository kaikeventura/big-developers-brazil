package br.com.kaikeventura.bdb.util;

import br.com.kaikeventura.bdb.dto.TechnologyDTO;
import br.com.kaikeventura.bdb.model.Technology;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TechnologyUtil {

    private final ClockUtil clockUtil;

    public Technology toNewTechnology(TechnologyDTO technologyDTO) {
        return new Technology(
                technologyDTO.getName(),
                technologyDTO.getReleaseDateOf(),
                technologyDTO.getTechnologyType(),
                true
        );
    }
}
