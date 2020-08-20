package br.com.kaikeventura.bdb.controller;

import br.com.kaikeventura.bdb.dto.TechnologyDTO;
import br.com.kaikeventura.bdb.model.User;
import br.com.kaikeventura.bdb.service.impl.TechnologyServiceImpl;
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
public class TechnologyController {

    private final TechnologyServiceImpl technologyService;

    @PostMapping("technologies")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<User>> createTechnology(@RequestBody @Valid final TechnologyDTO technologyDTO) {
        return Mono.just(new ResponseEntity(technologyService.saveTechnology(technologyDTO), HttpStatus.CREATED));
    }
}
