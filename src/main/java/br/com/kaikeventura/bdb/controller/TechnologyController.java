package br.com.kaikeventura.bdb.controller;

import br.com.kaikeventura.bdb.dto.UserDTO;
import br.com.kaikeventura.bdb.model.User;
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

    @PostMapping("technologies")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<User>> createTechnology(@RequestBody @Valid final UserDTO userDTO) {
        //return Mono.just(new ResponseEntity(userServiceImpl.saveAdmin(userDTO), HttpStatus.CREATED));
        return null;
    }
}
