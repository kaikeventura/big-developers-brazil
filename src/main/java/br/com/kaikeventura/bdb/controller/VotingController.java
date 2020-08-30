package br.com.kaikeventura.bdb.controller;

import br.com.kaikeventura.bdb.dto.VotingDTO;
import br.com.kaikeventura.bdb.model.Voting;
import br.com.kaikeventura.bdb.service.impl.VotingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/")
@RequiredArgsConstructor
public class VotingController {

    private final VotingServiceImpl votingService;

    @PostMapping("voting/{bigDebug}")
    @PreAuthorize("hasRole('USER')")
    public Mono<ResponseEntity<Voting>> createUserAdmin(
            @RequestHeader("Authorization") final String token,
            @RequestBody @Valid final VotingDTO votingDTO,
            @PathVariable("bigDebug") final String bigDebug
    ) {
        return Mono.just(new ResponseEntity(votingService.registerVote(votingDTO, bigDebug, token), HttpStatus.CREATED));
    }
}
