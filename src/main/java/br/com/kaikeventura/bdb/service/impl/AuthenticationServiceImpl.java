package br.com.kaikeventura.bdb.service.impl;

import br.com.kaikeventura.bdb.dto.LoginDTO;
import br.com.kaikeventura.bdb.dto.ResponseToken;
import br.com.kaikeventura.bdb.error.exception.EmailNotFoundException;
import br.com.kaikeventura.bdb.error.exception.IncorrectCredentialsException;
import br.com.kaikeventura.bdb.model.User;
import br.com.kaikeventura.bdb.repository.UserRepository;
import br.com.kaikeventura.bdb.service.AuthenticationService;
import br.com.kaikeventura.bdb.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    @Override
    public ResponseToken login(LoginDTO authenticationDTO) {
        final Optional<User> user = userRepository.findByEmailLikeIgnoreCase(authenticationDTO.getEmail());
        if (user.isPresent()) {
            if (new BCryptPasswordEncoder().matches(authenticationDTO.getPassword(), user.get().getPassword())) {
                return new ResponseToken(jwtUtil.generateToken(user.get()));
            } else {
                throw new IncorrectCredentialsException();
            }
        }
        throw new EmailNotFoundException();
    }
}
