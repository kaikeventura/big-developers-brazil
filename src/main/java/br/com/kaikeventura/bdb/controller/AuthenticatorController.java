package br.com.kaikeventura.bdb.controller;

import br.com.kaikeventura.bdb.dto.LoginDTO;
import br.com.kaikeventura.bdb.dto.ResponseToken;
import br.com.kaikeventura.bdb.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthenticatorController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseToken> login(@RequestBody @Valid LoginDTO authenticationDTO) {
        return ResponseEntity.ok(authenticationService.login(authenticationDTO));
    }
}
