package br.com.kaikeventura.bdb;

import br.com.kaikeventura.bdb.aux.Role;
import br.com.kaikeventura.bdb.aux.TechnologyType;
import br.com.kaikeventura.bdb.model.Technology;
import br.com.kaikeventura.bdb.model.User;
import br.com.kaikeventura.bdb.repository.TechnologyRepository;
import br.com.kaikeventura.bdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
@EnableMongoAuditing
public class BdbApplication {

	private final UserRepository userRepository;
	private final TechnologyRepository technologyRepository;

	@PostConstruct
	public void start() {
		if (userRepository.findByEmail("admin@bdb.com").isEmpty()) {
			userRepository.save(
					new User(
							"admin",
							"admin",
							LocalDate.parse("1998-01-06"),
							"admin@bdb.com",
							new BCryptPasswordEncoder().encode("123"),
							Arrays.asList(Role.ROLE_ADMIN),
							true
					)
			);
		}

		if (userRepository.findByEmail("kaike@gmail.com").isEmpty()) {
			userRepository.save(
					new User(
							"Kaike",
							"Ventura",
							LocalDate.parse("1998-01-06"),
							"kaike@gmail.com",
							new BCryptPasswordEncoder().encode("123"),
							Arrays.asList(Role.ROLE_USER),
							true
					)
			);
		}

		if (technologyRepository.findAll().isEmpty()) {
			technologyRepository.saveAll(
					Arrays.asList(
							new Technology(
									"Java",
									LocalDate.parse("1998-01-06"),
									TechnologyType.PROGRAMMING_LANGUAGE,
									true
							),
							new Technology(
									"Python",
									LocalDate.parse("1998-01-06"),
									TechnologyType.PROGRAMMING_LANGUAGE,
									true
							),
							new Technology(
									"C#",
									LocalDate.parse("1998-01-06"),
									TechnologyType.PROGRAMMING_LANGUAGE,
									true
							),
							new Technology(
									"Eclipse",
									LocalDate.parse("1998-01-06"),
									TechnologyType.IDE,
									true
							)
					)
			);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(BdbApplication.class, args);
	}

}
