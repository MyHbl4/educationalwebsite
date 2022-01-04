package com.moon.senla.educational_website;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class EducationalWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationalWebsiteApplication.class, args);
    }

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Topic top = new Topic();
//        top = topicService.findById(1);
//        System.out.println(top);
//        System.out.println(theoryService.findById(1));
//        System.out.println(userService.findById(1));

//        userRepository.save(new User("user3@gmail.com", "user3", "user3", "user3", "user",
//            null, null, Arrays.asList(Role.ROLE_USER),null));
//        userRepository.save(new User("user4@gmail.com", "user4", "user4", "user4", "user",
//            null, null, Arrays.asList(Role.ROLE_USER),null));
//        userRepository.save(new User("user5@gmail.com", "user5", "user5", "user5", "user",
//            null, null, Arrays.asList(Role.ROLE_USER),null));
//        userRepository.save(new User("admin2@gmail.com", "admin2", "admin2", "admin2", "admin",
//            null,null, Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN), null));
//
//        topicService.save(new Topic("JAVA", null, null));
//        topicService.save(new Topic("Python", null, null));
//        topicService.save(new Topic("C", null, null));
//
//        theoryRepository.save(new Theory("Java arrays", "some information",
//            topicService.getById(1L), LocalDate.now()));
//        theoryRepository.save(new Theory("Java path", "some information",
//            topicService.getById(1L), LocalDate.now()));
//        theoryRepository.save(new Theory("C underworld", "some information",
//            topicService.getById(3L), LocalDate.now()));
//        theoryRepository.save(new Theory("Python for beginner", "some information",
//            topicService.getById(2L), LocalDate.now()));
//        theoryRepository.save(new Theory("Go away", "some information",
//            topicService.getById(3L), LocalDate.now()));
//        theoryRepository.save(new Theory("Python start", "some information",
//            topicService.getById(2L), LocalDate.now()));
//    }
}
