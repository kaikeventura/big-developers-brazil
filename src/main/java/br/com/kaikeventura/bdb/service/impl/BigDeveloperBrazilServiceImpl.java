package br.com.kaikeventura.bdb.service.impl;

import br.com.kaikeventura.bdb.aux.TechnologyType;
import br.com.kaikeventura.bdb.dto.BigDeveloperBrazilDTO;
import br.com.kaikeventura.bdb.error.exception.BigDeveloperBrazilAlreadyRegisteredException;
import br.com.kaikeventura.bdb.error.exception.IncorrectTechnologyTypeException;
import br.com.kaikeventura.bdb.error.exception.TechnologyNotFoundException;
import br.com.kaikeventura.bdb.model.BigDeveloperBrazil;
import br.com.kaikeventura.bdb.model.Technology;
import br.com.kaikeventura.bdb.repository.BigDeveloperBrazilRepository;
import br.com.kaikeventura.bdb.repository.BigDeveloperBrazilRepositoryReactive;
import br.com.kaikeventura.bdb.repository.TechnologyRepository;
import br.com.kaikeventura.bdb.service.BigDeveloperBrazilService;
import br.com.kaikeventura.bdb.util.BigDeveloperBrazilUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BigDeveloperBrazilServiceImpl implements BigDeveloperBrazilService {

    private final BigDeveloperBrazilRepositoryReactive bigDeveloperBrazilRepositoryReactive;
    private final BigDeveloperBrazilRepository bigDeveloperBrazilRepository;
    private final BigDeveloperBrazilUtil bigDeveloperBrazilUtil;
    private final TechnologyRepository technologyRepository;

    @Override
    public Mono<BigDeveloperBrazil> save(BigDeveloperBrazilDTO bigDeveloperBrazilDTO) {
        verifyIfBigDeveloperBrazilIsExists(bigDeveloperBrazilDTO.getName());
        verifyIfTechnologiesIsValid(bigDeveloperBrazilDTO.getTechnologies(), bigDeveloperBrazilDTO.getTechnologyType());

        return bigDeveloperBrazilRepositoryReactive.save(
                bigDeveloperBrazilUtil.toBigDeveloperBrazil(bigDeveloperBrazilDTO)
        );
    }

    private void verifyIfBigDeveloperBrazilIsExists(String name) {
        bigDeveloperBrazilRepository.findByName(name).ifPresent(ex -> {
            throw new BigDeveloperBrazilAlreadyRegisteredException();
        });
    }

    private void verifyIfTechnologiesIsValid(List<String> technologies, TechnologyType technologyType) {
        technologies.forEach(tech -> {
            final Optional<Technology> actualTechnology = technologyRepository.findByName(tech);
            if (actualTechnology.isEmpty()) {
                throw new TechnologyNotFoundException();
            }
            if (!actualTechnology.get().getTechnologyType().equals(technologyType)) {
                throw new IncorrectTechnologyTypeException();
            }
        });
    }
}
