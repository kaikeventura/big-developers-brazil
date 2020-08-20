package br.com.kaikeventura.bdb.service;

import br.com.kaikeventura.bdb.dto.TechnologyDTO;
import br.com.kaikeventura.bdb.model.Technology;
import reactor.core.publisher.Mono;

public interface TechnologyService {
    Mono<Technology> saveTechnology(TechnologyDTO technologyDTO);
}
