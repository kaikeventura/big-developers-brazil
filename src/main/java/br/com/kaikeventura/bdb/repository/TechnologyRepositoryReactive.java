package br.com.kaikeventura.bdb.repository;

import br.com.kaikeventura.bdb.model.Technology;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TechnologyRepositoryReactive extends ReactiveCrudRepository<Technology, String> {
}
