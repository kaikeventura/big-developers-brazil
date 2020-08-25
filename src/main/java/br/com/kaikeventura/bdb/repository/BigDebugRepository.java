package br.com.kaikeventura.bdb.repository;

import br.com.kaikeventura.bdb.model.BigDebug;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BigDebugRepository extends MongoRepository<BigDebug, String> {
    Optional<BigDebug> findByNameLikeIgnoreCase(String name);
}
