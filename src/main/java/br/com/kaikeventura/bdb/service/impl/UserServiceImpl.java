package br.com.kaikeventura.bdb.service.impl;

import br.com.kaikeventura.bdb.dto.UserDTO;
import br.com.kaikeventura.bdb.error.exception.EmailAlreadyRegisteredException;
import br.com.kaikeventura.bdb.model.User;
import br.com.kaikeventura.bdb.repository.UserRepository;
import br.com.kaikeventura.bdb.repository.UserRepositoryReactive;
import br.com.kaikeventura.bdb.service.UserService;
import br.com.kaikeventura.bdb.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRepositoryReactive userRepositoryReactive;
    private final UserUtil userUtil;

    @Override
    public Mono<User> saveCommon(UserDTO userDTO) {
        verifyIfEmailIsExists(userDTO.getEmail());
        return userRepositoryReactive.save(userUtil.toNewUser(userDTO));
    }

    @Override
    public Mono<User> saveAdmin(UserDTO userDTO) {
        verifyIfEmailIsExists(userDTO.getEmail());
        return userRepositoryReactive.save(userUtil.toNewAdminUser(userDTO));
    }

    private void verifyIfEmailIsExists(String email) {
        userRepository.findByEmail(email).ifPresent(ex -> {
            throw new EmailAlreadyRegisteredException();
        });
    }
}
