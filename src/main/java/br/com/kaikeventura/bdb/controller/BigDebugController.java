package br.com.kaikeventura.bdb.controller;

import br.com.kaikeventura.bdb.dto.BigDebugDTO;
import br.com.kaikeventura.bdb.model.BigDebug;
import br.com.kaikeventura.bdb.service.impl.BigDebugServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BigDebugController {

    private final BigDebugServiceImpl bigDebugService;

    @PostMapping("admin/big-debug")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<BigDebug>> createBigDebug(@Valid @RequestBody final BigDebugDTO bigDebugDTO) {
        return Mono.just(new ResponseEntity(bigDebugService.save(bigDebugDTO), HttpStatus.CREATED));
    }

    @PatchMapping("admin/big-debug/enable/{bigDebug}")
    @PreAuthorize("hasRole('ADMIN')")
    @CachePut(value = "big-debug", key = "#bigDebug")
    public Mono<ResponseEntity<BigDebug>> enableVisibilityBigDebug(@PathVariable("bigDebug") final String bigDebug) {
        bigDebugService.enableVisibility(bigDebug);
        return Mono.just(new ResponseEntity(HttpStatus.NO_CONTENT));
    }

    @PatchMapping("admin/big-debug/disable/{bigDebug}")
    @PreAuthorize("hasRole('ADMIN')")
    @CachePut(value = "big-debug", key = "#bigDebug")
    public Mono<ResponseEntity<BigDebug>> disableVisibilityBigDebug(@PathVariable("bigDebug") final String bigDebug) {
        bigDebugService.disableVisibility(bigDebug);
        return Mono.just(new ResponseEntity(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("admin/big-debug/{bigDebug}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<BigDebug>> disableBigDebug(@PathVariable("bigDebug") final String bigDebug) {
        bigDebugService.disable(bigDebug);
        return Mono.just(new ResponseEntity(HttpStatus.NO_CONTENT));
    }

    @GetMapping("big-debug/active")
    @Cacheable("big-debug")
    public Mono<ResponseEntity<BigDebug>> bigDebugActive() {
        return Mono.just(new ResponseEntity(bigDebugService.getBigDebugActive(), HttpStatus.OK));
    }
}
