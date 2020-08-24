package br.com.kaikeventura.bdb;

import br.com.kaikeventura.bdb.aux.Role;
import br.com.kaikeventura.bdb.model.User;
import br.com.kaikeventura.bdb.repository.UserRepository;
import br.com.kaikeventura.bdb.util.ClockUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class BdbApplication {

	private final UserRepository userRepository;
	private final ClockUtil clockUtil;

	@PostConstruct
	public void start() {
		if (userRepository.findByEmailLikeIgnoreCase("admin@bdb.com.br").isEmpty()) {
			userRepository.save(
					new User(
							"admin",
							"admin",
							LocalDate.parse("1998-01-06"),
							"admin@bdb.com.br",
							new BCryptPasswordEncoder().encode("123"),
							Arrays.asList(Role.ROLE_ADMIN),
							true
					)
			);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(BdbApplication.class, args);
	}

}
