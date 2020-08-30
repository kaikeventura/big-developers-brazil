package br.com.kaikeventura.bdb.repository;

import br.com.kaikeventura.bdb.model.BigDeveloperBrazil;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BigDeveloperBrazilRepository extends MongoRepository<BigDeveloperBrazil, String> {
    Optional<BigDeveloperBrazil> findByName(String name);
}
