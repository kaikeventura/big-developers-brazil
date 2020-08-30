package br.com.kaikeventura.bdb.repository;

import br.com.kaikeventura.bdb.model.Voting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotingRepository extends MongoRepository<Voting, String> {
}
