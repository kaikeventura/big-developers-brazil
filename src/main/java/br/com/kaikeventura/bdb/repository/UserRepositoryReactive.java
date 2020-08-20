package br.com.kaikeventura.bdb.repository;

import br.com.kaikeventura.bdb.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepositoryReactive extends ReactiveCrudRepository<User, String> {
}
