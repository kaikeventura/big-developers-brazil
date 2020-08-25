package br.com.kaikeventura.bdb.util;

import br.com.kaikeventura.bdb.dto.BigDeveloperBrazilDTO;
import br.com.kaikeventura.bdb.model.BigDeveloperBrazil;
import br.com.kaikeventura.bdb.model.TechnologyState;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BigDeveloperBrazilUtil {

    public BigDeveloperBrazil toBigDeveloperBrazil(BigDeveloperBrazilDTO bigDeveloperBrazilDTO) {
        return new BigDeveloperBrazil(
                bigDeveloperBrazilDTO.getName(),
                bigDeveloperBrazilDTO.getTechnologyType(),
                toTechnologyState(bigDeveloperBrazilDTO.getTechnologies()),
                new ArrayList<>(),
                true
        );
    }

    private ArrayList<TechnologyState> toTechnologyState(List<String> technologies) {
        ArrayList<TechnologyState> technologyStates = new ArrayList<>();
        technologies.forEach(tech -> technologyStates.add(new TechnologyState(tech, true)));

        return technologyStates;
    }
}
