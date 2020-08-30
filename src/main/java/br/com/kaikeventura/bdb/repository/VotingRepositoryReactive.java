package br.com.kaikeventura.bdb.repository;

import br.com.kaikeventura.bdb.model.Voting;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VotingRepositoryReactive extends ReactiveCrudRepository<Voting, String> {
}
