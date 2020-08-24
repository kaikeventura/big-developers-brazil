package br.com.kaikeventura.bdb.service;

import br.com.kaikeventura.bdb.dto.BigDeveloperBrazilDTO;
import br.com.kaikeventura.bdb.model.BigDeveloperBrazil;
import reactor.core.publisher.Mono;

public interface BigDeveloperBrazilService {
    Mono<BigDeveloperBrazil> save(BigDeveloperBrazilDTO bigDeveloperBrazilDTO);
}
