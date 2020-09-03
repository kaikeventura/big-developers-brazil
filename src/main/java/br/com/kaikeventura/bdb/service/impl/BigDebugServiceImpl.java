package br.com.kaikeventura.bdb.service.impl;

import br.com.kaikeventura.bdb.dto.BigDebugDTO;
import br.com.kaikeventura.bdb.error.exception.*;
import br.com.kaikeventura.bdb.model.BigDebug;
import br.com.kaikeventura.bdb.model.BigDeveloperBrazil;
import br.com.kaikeventura.bdb.repository.BigDebugRepository;
import br.com.kaikeventura.bdb.repository.BigDebugRepositoryReactive;
import br.com.kaikeventura.bdb.repository.BigDeveloperBrazilRepository;
import br.com.kaikeventura.bdb.service.BigDebugService;
import br.com.kaikeventura.bdb.util.BigDebugCacheUtil;
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
    private final BigDebugCacheUtil bigDebugCacheUtil;

    @Override
    public Mono<BigDebug> save(BigDebugDTO bigDebugDTO) {
        verifyIfBigDebugIsExists(bigDebugDTO.getName());
        verifyIfBigDebugIsValid(bigDebugDTO);

        return bigDebugRepositoryReactive.save(bigDebugUtil.toBigDebug(bigDebugDTO));
    }

    @Override
    public void enableVisibility(String bigDebug) {
        BigDebug actualBigDebug = getBigDebug(bigDebug);
        actualBigDebug.setVisible(true);
        bigDebugCacheUtil.putBigDebug(actualBigDebug);

        bigDebugRepository.save(actualBigDebug);
    }

    @Override
    public void disableVisibility(String bigDebug) {
        BigDebug actualBigDebug = getBigDebug(bigDebug);
        actualBigDebug.setVisible(false);
        bigDebugCacheUtil.deleteBigDebug();

        bigDebugRepository.save(actualBigDebug);
    }

    @Override
    public void disable(String bigDebug) {
        BigDebug actualBigDebug = getBigDebug(bigDebug);
        actualBigDebug.setVisible(false);
        actualBigDebug.setActive(false);
        bigDebugCacheUtil.deleteBigDebug();

        bigDebugRepository.save(actualBigDebug);
    }

    @Override
    public Mono<BigDebug> getBigDebugActive() {
        return Mono.just(bigDebugCacheUtil.getBigDebug());
    }

    private void verifyIfBigDebugIsExists(String name) {
        bigDebugRepository.findByName(name).ifPresent(ex -> {
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
                = bigDeveloperBrazilRepository.findByName(bigDeveloperBrazil);
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

    private BigDebug getBigDebug(String bigDebugName) {
        Optional<BigDebug> bigDebug = bigDebugRepository.findByName(bigDebugName);
        if (bigDebug.isEmpty()) {
            throw new BigDebugNotFoundException();
        }
        if (!bigDebug.get().getActive()) {
            throw new BigDebugNotAvailableException();
        }

        return bigDebug.get();
    }
}
