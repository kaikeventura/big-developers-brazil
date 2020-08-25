package br.com.kaikeventura.bdb.util;

import br.com.kaikeventura.bdb.dto.BigDebugDTO;
import br.com.kaikeventura.bdb.model.BigDebug;
import br.com.kaikeventura.bdb.model.TechnologyBigDebug;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class BigDebugUtil {

    public BigDebug toBigDebug(BigDebugDTO bigDebugDTO) {
        return new BigDebug(
                bigDebugDTO.getName(),
                bigDebugDTO.getBigDeveloperBrazil(),
                toTechnologies(bigDebugDTO.getTechnologies()),
                false,
                true
        );
    }

    private ArrayList<TechnologyBigDebug> toTechnologies(List<String> technologies) {
        ArrayList<TechnologyBigDebug> technologyBigDebugs = new ArrayList<>();
        technologies.forEach(tech -> technologyBigDebugs.add(new TechnologyBigDebug(0, tech)));
        IntStream.range(0, technologies.size()).forEach(tech -> technologyBigDebugs.get(tech).setId(tech + 1));

        return technologyBigDebugs;
    }

}
