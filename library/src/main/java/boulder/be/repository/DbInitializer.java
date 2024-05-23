package boulder.be.repository;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import boulder.be.model.User;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {
    private UserRepository userRepository;

    @Autowired
    public DbInitializer(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @PostConstruct
    public void initialize(){
        User user1 = new User("Wiebe", "Delvaux", LocalDate.of(2004, 11, 24), "wiebe.delvaux@gmail.com", true);
        User user2 = new User("John", "Doe", LocalDate.of(1990, 5, 15), "john.doe@example.com", true);
        User user3 = new User("Jane", "Smith", LocalDate.of(1985, 8, 22), "jane.smith@example.com", false);
        User user4 = new User("Alice", "Johnson", LocalDate.of(1995, 2, 10), "alice.johnson@example.com", true);
        User user5 = new User("Bob", "Brown", LocalDate.of(2000, 7, 30), "bob.brown@example.com", false);
        User user6 = new User("Charlie", "Davis", LocalDate.of(1998, 12, 5), "charlie.davis@example.com", true);    
    
        userRepository.saveAll(Arrays.asList(user1,user2,user3,user4,user5,user6));
    }

}
