package br.com.kaikeventura.bdb.controller;

import br.com.kaikeventura.bdb.dto.UserDTO;
import br.com.kaikeventura.bdb.model.User;
import br.com.kaikeventura.bdb.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("users")
    public ResponseEntity<Mono<User>> createUser(@RequestBody @Valid final UserDTO userDTO) {
        return new ResponseEntity(userServiceImpl.saveCommon(userDTO), HttpStatus.CREATED);
    }

    @PostMapping("admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<User>> createUserAdmin(@RequestBody @Valid final UserDTO userDTO) {
        return Mono.just(new ResponseEntity(userServiceImpl.saveAdmin(userDTO), HttpStatus.CREATED));
    }

    @PatchMapping("users/forget-password/{email}")
    public ResponseEntity<Mono<Void>> forgetPassword(@PathVariable("email") final String email) {
        return new ResponseEntity(userServiceImpl.forgetPassword(email), HttpStatus.NO_CONTENT);
    }

}
