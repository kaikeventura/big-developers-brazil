package br.com.kaikeventura.bdb.repository;

import br.com.kaikeventura.bdb.model.Technology;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TechnologyRepository extends MongoRepository<Technology, String> {
    Optional<Technology> findByNameLikeIgnoreCase(String name);
}
