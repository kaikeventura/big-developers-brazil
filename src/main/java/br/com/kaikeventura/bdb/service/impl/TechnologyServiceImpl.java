package br.com.kaikeventura.bdb.service.impl;

import br.com.kaikeventura.bdb.dto.TechnologyDTO;
import br.com.kaikeventura.bdb.error.exception.TechnologyAlreadyRegisteredException;
import br.com.kaikeventura.bdb.model.Technology;
import br.com.kaikeventura.bdb.repository.TechnologyRepository;
import br.com.kaikeventura.bdb.repository.TechnologyRepositoryReactive;
import br.com.kaikeventura.bdb.service.TechnologyService;
import br.com.kaikeventura.bdb.util.TechnologyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final TechnologyRepositoryReactive technologyRepositoryReactive;
    private final TechnologyUtil technologyUtil;

    @Override
    public Mono<Technology> saveTechnology(TechnologyDTO technologyDTO) {
        verifyIfTechnologyIsExists(technologyDTO.getName());
        return technologyRepositoryReactive.save(technologyUtil.toNewTechnology(technologyDTO));
    }

    private void verifyIfTechnologyIsExists(String name) {
        technologyRepository.findByName(name).ifPresent(ex -> {
            throw new TechnologyAlreadyRegisteredException();
        });
    }
}
