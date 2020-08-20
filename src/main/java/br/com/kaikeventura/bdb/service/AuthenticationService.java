package br.com.kaikeventura.bdb.service;

import br.com.kaikeventura.bdb.dto.LoginDTO;
import br.com.kaikeventura.bdb.dto.ResponseToken;

public interface AuthenticationService {
    ResponseToken login(LoginDTO authenticationDTO);
}
