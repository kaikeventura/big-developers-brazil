package br.com.kaikeventura.bdb.service;

import br.com.kaikeventura.bdb.dto.VotingDTO;
import br.com.kaikeventura.bdb.model.Voting;
import reactor.core.publisher.Mono;

public interface VotingService {
    Mono<Voting> registerVote(VotingDTO votingDTO, String bigDebug, String token);
}
