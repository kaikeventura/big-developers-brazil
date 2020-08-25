package br.com.kaikeventura.bdb.service.impl;

import br.com.kaikeventura.bdb.dto.BigDebugDTO;
import br.com.kaikeventura.bdb.error.exception.BigDebugAlreadyRegisteredException;
import br.com.kaikeventura.bdb.error.exception.InvalidBigDeveloperBrazilException;
import br.com.kaikeventura.bdb.error.exception.TechnologyAlreadyEliminatedException;
import br.com.kaikeventura.bdb.error.exception.TechnologyNotAvailableInThisBigDeveloperBrazilException;
import br.com.kaikeventura.bdb.model.BigDebug;
import br.com.kaikeventura.bdb.model.BigDeveloperBrazil;
import br.com.kaikeventura.bdb.repository.BigDebugRepository;
import br.com.kaikeventura.bdb.repository.BigDebugRepositoryReactive;
import br.com.kaikeventura.bdb.repository.BigDeveloperBrazilRepository;
import br.com.kaikeventura.bdb.service.BigDebugService;
import br.com.kaikeventura.bdb.util.BigDebugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BigDebugServiceImpl implements BigDebugService {

    private final BigDebugRepositoryReactive bigDebugRepositoryReactive;
    private final BigDebugRepository bigDebugRepository;
    private final BigDebugUtil bigDebugUtil;
    private final BigDeveloperBrazilRepository bigDeveloperBrazilRepository;

    @Override
    public Mono<BigDebug> save(BigDebugDTO bigDebugDTO) {
        verifyIfBigDebugIsExists(bigDebugDTO.getName());
        verifyIfBigDebugIsValid(bigDebugDTO);

        return bigDebugRepositoryReactive.save(bigDebugUtil.toBigDebug(bigDebugDTO));
    }

    private void verifyIfBigDebugIsExists(String name) {
        bigDebugRepository.findByNameLikeIgnoreCase(name).ifPresent(ex -> {
            throw new BigDebugAlreadyRegisteredException();
        });
    }

    private void verifyIfBigDebugIsValid(BigDebugDTO bigDebugDTO) {
        BigDeveloperBrazil actualBigDeveloperBrazil
                = verifyIfBigDeveloperBrazilIsExists(bigDebugDTO.getBigDeveloperBrazil());
        bigDebugDTO.getTechnologies().forEach(tech -> {
            if (
                    actualBigDeveloperBrazil
                            .getTechnologies()
                            .stream()
                            .filter(actualTech -> actualTech.getTechnologyName().equals(tech))
                            .findFirst()
                            .isEmpty()
            ) {
                throw new TechnologyNotAvailableInThisBigDeveloperBrazilException();
            }
            if (
                    actualBigDeveloperBrazil
                            .getTechnologies()
                            .stream()
                            .filter(actualTech -> actualTech.getTechnologyName().equals(tech))
                            .anyMatch(techSituation -> techSituation.getInTheGame().equals(false))
            ) {
                throw new TechnologyAlreadyEliminatedException();
            }
        });
        updateBigDeveloperBrazil(actualBigDeveloperBrazil, bigDebugDTO.getName());
    }

    private BigDeveloperBrazil verifyIfBigDeveloperBrazilIsExists(String bigDeveloperBrazil) {
        final Optional<BigDeveloperBrazil> actualBigDeveloperBrazil
                = bigDeveloperBrazilRepository.findByNameLikeIgnoreCase(bigDeveloperBrazil);
        if (
                actualBigDeveloperBrazil.isPresent()
                && !actualBigDeveloperBrazil.get().getActive()
                || actualBigDeveloperBrazil.isEmpty()
        ) {
            throw new InvalidBigDeveloperBrazilException();
        }

        return actualBigDeveloperBrazil.get();
    }

    private void updateBigDeveloperBrazil(BigDeveloperBrazil bigDeveloperBrazil, String bigDebug) {
        if (bigDeveloperBrazil.getBigDebugs().isEmpty()) {
            bigDeveloperBrazil.setBigDebugs(Collections.singletonList(bigDebug));
        }
        else {
            bigDeveloperBrazil.getBigDebugs().add(bigDebug);
        }
        bigDeveloperBrazilRepository.save(bigDeveloperBrazil);
    }
}
