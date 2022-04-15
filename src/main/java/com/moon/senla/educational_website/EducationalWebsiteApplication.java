package com.moon.senla.educational_website;

import com.moon.senla.educational_website.dao.RoleRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.model.Role;
import com.moon.senla.educational_website.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class EducationalWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationalWebsiteApplication.class, args);
    }

    //        @Bean
//    CommandLineRunner runner(GroupRepository repository) {
//        return args -> {
//            Group group = new Group("pro java",  new Course(), 10);
//
//            repository.insert(group);
//        };
//    }
//    @Bean
//    CommandLineRunner runner(CourseRepository repository) {
//        return args -> {
//            Course course = new Course("pro java", 100,  new User(), new Topic());
//
//            repository.insert(course);
//        };
//    }
//            @Bean
//    CommandLineRunner runner(TheoryRepository repository) {
//        return args -> {
//            Theory theory1 = new Theory("pro java", "some text", new Topic(), new User());
//
//            repository.insert(theory1);
//        };
//    }
//        @Bean
//    CommandLineRunner runner(TopicRepository repository) {
//        return args -> {
//            Topic topic1 = new Topic("Java");
//            Topic topic2 = new Topic("C++");
//            repository.insert(topic1);
//            repository.insert(topic2);
//        };
//    }
    @Bean
    CommandLineRunner runner(UserRepository userRepository, RoleRepository repository) {
        return args -> {
            Role roleUser = new Role("ROLE_USER");
            Role roleAdmin = new Role("ROLE_ADMIN");
            List<Role> rolesUser = new ArrayList<>();
            rolesUser.add(roleUser);
            List<Role> rolesAdmin = new ArrayList<>();
            rolesAdmin.add(roleAdmin);
            rolesAdmin.add(roleUser);
            User user = new User("user@gmail.com", "user", "user",
                "user", "user", rolesUser);
            User admin = new User("admin@gmail.com", "admin", "admin",
                "admin", "admin", rolesAdmin);

            if (repository.findByName("ROLE_USER") == null) {
                repository.insert(roleAdmin);
            }
            if (repository.findByName("ROLE_ADMIN") == null) {
                repository.insert(roleUser);
            }
            if (userRepository.findByUsername("user") == null
                && userRepository.findUserByEmail("user@gmail.com") == null) {
                userRepository.insert(user);
            }
            if (userRepository.findByUsername("admin") == null
                && userRepository.findUserByEmail("admin@gmail.com") == null) {
                userRepository.insert(admin);
            }
        };
    }
}
