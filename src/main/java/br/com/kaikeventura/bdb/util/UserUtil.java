package br.com.kaikeventura.bdb.util;

import br.com.kaikeventura.bdb.aux.Role;
import br.com.kaikeventura.bdb.dto.UpdateUserPasswordDTO;
import br.com.kaikeventura.bdb.dto.UserDTO;
import br.com.kaikeventura.bdb.error.exception.PasswordsNotMatchException;
import br.com.kaikeventura.bdb.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserUtil {

    public User toNewUser(UserDTO userDTO) {
        return new User(
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getDateOfBirth(),
                userDTO.getEmail(),
                encodePassword(userDTO.getPassword()),
                Collections.singletonList(Role.ROLE_USER),
                true
        );
    }

    public User toNewAdminUser(UserDTO userDTO) {
        return new User(
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getDateOfBirth(),
                userDTO.getEmail(),
                encodePassword(userDTO.getPassword()),
                Collections.singletonList(Role.ROLE_ADMIN),
                true
        );
    }

    public String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public void checkIfNewPasswordIsValid(UpdateUserPasswordDTO updateUserPasswordDTO) {
        if (!updateUserPasswordDTO.getNewPassword().equals(updateUserPasswordDTO.getConfirmNewPassword())) {
            throw new PasswordsNotMatchException();
        }
    }
}
