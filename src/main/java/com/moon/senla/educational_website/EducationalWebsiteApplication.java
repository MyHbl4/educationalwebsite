package com.moon.senla.educational_website;

import com.moon.senla.educational_website.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class EducationalWebsiteApplication implements ApplicationRunner {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(EducationalWebsiteApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        userRepository.save(new User("user@gmail.com", "user", "user", "user",
//            Arrays.asList(Role.ROLE_USER)));
//        userRepository.save(new User("admin@gmail.com", "admin", "admin", "admin",
//            Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
    }
}
