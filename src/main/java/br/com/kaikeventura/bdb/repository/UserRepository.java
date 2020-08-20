package br.com.kaikeventura.bdb.repository;

import br.com.kaikeventura.bdb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmailLikeIgnoreCase(String email);
}
