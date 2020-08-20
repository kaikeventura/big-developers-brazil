package br.com.kaikeventura.bdb.util;

import br.com.kaikeventura.bdb.dto.UserDTO;
import br.com.kaikeventura.bdb.aux.Role;
import br.com.kaikeventura.bdb.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final ClockUtil clockUtil;

    public User toNewUser(UserDTO userDTO) {
        return new User(
                UUID.randomUUID().toString(),
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getDateOfBirth(),
                userDTO.getEmail(),
                encodePassword(userDTO.getPassword()),
                Arrays.asList(Role.ROLE_USER),
                true,
                clockUtil.instantNow(),
                null,
                null
        );
    }

    public User toNewAdminUser(UserDTO userDTO) {
        return new User(
                UUID.randomUUID().toString(),
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getDateOfBirth(),
                userDTO.getEmail(),
                encodePassword(userDTO.getPassword()),
                Arrays.asList(Role.ROLE_ADMIN),
                true,
                clockUtil.instantNow(),
                null,
                null
        );
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
