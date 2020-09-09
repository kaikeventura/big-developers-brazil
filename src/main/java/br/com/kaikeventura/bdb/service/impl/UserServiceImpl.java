package br.com.kaikeventura.bdb.service.impl;

import br.com.kaikeventura.bdb.dto.UpdateUserPasswordDTO;
import br.com.kaikeventura.bdb.dto.UserDTO;
import br.com.kaikeventura.bdb.error.exception.*;
import br.com.kaikeventura.bdb.model.User;
import br.com.kaikeventura.bdb.repository.UserRepository;
import br.com.kaikeventura.bdb.repository.UserRepositoryReactive;
import br.com.kaikeventura.bdb.service.UserService;
import br.com.kaikeventura.bdb.util.JWTUtil;
import br.com.kaikeventura.bdb.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRepositoryReactive userRepositoryReactive;
    private final UserUtil userUtil;
    private final JavaMailSender javaMailSender;
    private final JWTUtil jwtUtil;

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

    @Override
    public Mono<Void> forgetPassword(String email) {
        final String newPassword = generatedNewPassword();
        User user = getUser(email);
        user.setPassword(userUtil.encodePassword(newPassword));
        userRepository.save(user);

        javaMailSender.send(buildEmailWithNewPassword(email, newPassword));

        return Mono.empty();
    }

    @Override
    public Mono<Void> updatePassword(String token, UpdateUserPasswordDTO updateUserPasswordDTO) {
        User user = getUser(jwtUtil.getUsernameFromToken(token.replace("Bearer ", "")));
        userUtil.checkIfNewPasswordIsValid(updateUserPasswordDTO);
        checkIfOldPasswordIsCorrect(user.getPassword(), updateUserPasswordDTO.getOldPassword());
        user.setPassword(userUtil.encodePassword(updateUserPasswordDTO.getNewPassword()));

        userRepository.save(user);

        return Mono.empty();
    }

    private void verifyIfEmailIsExists(String email) {
        userRepository.findByEmail(email).ifPresent(ex -> {
            throw new EmailAlreadyRegisteredException();
        });
    }

    private User getUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new EmailNotFoundException();
        }
        if (!user.get().getActive()) {
            throw new DisabledUserException();
        }

        return user.get();
    }

    private String generatedNewPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return "ERROR_PASS";
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        return gen.generatePassword(10, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
    }

    private SimpleMailMessage buildEmailWithNewPassword(String email, String newPassword) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("New Password");
        msg.setText(newPassword);

        return msg;
    }

    private void checkIfOldPasswordIsCorrect(String actualPassword, String passwordEntered) {
        if (!new BCryptPasswordEncoder().matches(passwordEntered, actualPassword)) {
            throw new PasswordsNotMatchException();
        }
    }
}
