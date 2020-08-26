package br.com.kaikeventura.bdb.service;

import br.com.kaikeventura.bdb.dto.BigDebugDTO;
import br.com.kaikeventura.bdb.model.BigDebug;
import reactor.core.publisher.Mono;

public interface BigDebugService {
    Mono<BigDebug> save(BigDebugDTO bigDebugDTO);
}