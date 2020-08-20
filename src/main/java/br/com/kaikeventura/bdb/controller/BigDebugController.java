package br.com.kaikeventura.bdb.controller;

import br.com.kaikeventura.bdb.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BigDebugController {

    @PostMapping("admin/big-debug")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<User>> createBigDebug() {
        //return Mono.just(new ResponseEntity(userServiceImpl.saveAdmin(userDTO), HttpStatus.CREATED));
        return null;
    }

}
