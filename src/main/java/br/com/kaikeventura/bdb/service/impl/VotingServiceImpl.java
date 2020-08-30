package br.com.kaikeventura.bdb.service.impl;

import br.com.kaikeventura.bdb.dto.VotingDTO;
import br.com.kaikeventura.bdb.error.exception.BigDebugNotAvailableException;
import br.com.kaikeventura.bdb.error.exception.BigDebugNotFoundException;
import br.com.kaikeventura.bdb.error.exception.TechnologyNotFoundException;
import br.com.kaikeventura.bdb.model.BigDebug;
import br.com.kaikeventura.bdb.model.TechnologyBigDebug;
import br.com.kaikeventura.bdb.model.Voting;
import br.com.kaikeventura.bdb.repository.BigDebugRepository;
import br.com.kaikeventura.bdb.repository.VotingRepositoryReactive;
import br.com.kaikeventura.bdb.service.VotingService;
import br.com.kaikeventura.bdb.util.VotingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotingServiceImpl implements VotingService {

    private final VotingRepositoryReactive votingRepositoryReactive;
    private final VotingUtil votingUtil;
    private final BigDebugRepository bigDebugRepository;

    @Override
    public Mono<Voting> registerVote(VotingDTO votingDTO, String bigDebug, String token) {
        BigDebug actualBigDebug = getBigDebug(bigDebug);
        checkIfIdTechnologyIsExists(actualBigDebug.getTechnologiesBigDebug(), votingDTO.getTechnologyBigDebugId());

        return votingRepositoryReactive.save(
                votingUtil.toVoting(
                        votingDTO,
                        actualBigDebug.getName(),
                        actualBigDebug.getBigDeveloperBrazil(),
                        token
                )
        );
    }

    private BigDebug getBigDebug(String bigDebug) {
        Optional<BigDebug> actualBigDebug = bigDebugRepository.findByName(bigDebug);
        if (actualBigDebug.isEmpty()) {
            throw new BigDebugNotFoundException();
        }
        if (!actualBigDebug.get().getVisible() || !actualBigDebug.get().getActive()) {
            throw new BigDebugNotAvailableException();
        }
        return actualBigDebug.get();
    }

    private void checkIfIdTechnologyIsExists(List<TechnologyBigDebug> technologiesBigDebug, int technologyBigDebugId) {
        if (
                technologiesBigDebug
                        .stream()
                        .filter(tech -> tech.getId() == technologyBigDebugId)
                        .findFirst()
                        .isEmpty()
        )  {
                throw new TechnologyNotFoundException();
        }
    }

}
