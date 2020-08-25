package br.com.kaikeventura.bdb.repository;

import br.com.kaikeventura.bdb.model.BigDebug;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BigDebugRepositoryReactive extends ReactiveCrudRepository<BigDebug, String> {
}
