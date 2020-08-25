package br.com.kaikeventura.bdb.controller;

import br.com.kaikeventura.bdb.dto.BigDebugDTO;
import br.com.kaikeventura.bdb.model.BigDebug;
import br.com.kaikeventura.bdb.service.impl.BigDebugServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
public class BigDebugController {

    private final BigDebugServiceImpl bigDebugService;

    @PostMapping("big-debug")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<BigDebug>> createBigDebug(@Valid @RequestBody final BigDebugDTO bigDebugDTO) {
        return Mono.just(new ResponseEntity(bigDebugService.save(bigDebugDTO), HttpStatus.CREATED));
    }

}
