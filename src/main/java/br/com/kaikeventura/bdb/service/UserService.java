package br.com.kaikeventura.bdb.service;

import br.com.kaikeventura.bdb.dto.UserDTO;
import br.com.kaikeventura.bdb.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> saveCommon(UserDTO userDTO);
    Mono<User> saveAdmin(UserDTO userDTO);
    Mono<Void> forgetPassword(String email);
}
