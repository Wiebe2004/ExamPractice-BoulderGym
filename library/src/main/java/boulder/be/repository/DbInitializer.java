package boulder.be.repository;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import boulder.be.model.Subscription;
import boulder.be.model.User;
import boulder.be.service.UserService;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
    private UserService userService;

    @Autowired
    public DbInitializer(UserRepository userRepository,SubscriptionRepository subscriptionRepository,UserService userService){
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;

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
        
        Subscription subscription1 = new Subscription("1MONTH", LocalDate.of(2024,5,23));
        Subscription subscription2 = new Subscription("3MONTH", LocalDate.of(2024,1,20));
        Subscription subscription3 = new Subscription("6MONTH", LocalDate.of(2024,3,4));
        Subscription subscription4 = new Subscription("6MONTH", LocalDate.of(2024,5,13));
        Subscription subscription5 = new Subscription("3MONTH", LocalDate.of(2024,2,5));
        Subscription subscription6 = new Subscription("6MONTH", LocalDate.of(2023,2,2));
        
        subscriptionRepository.saveAll(Arrays.asList(subscription1,subscription2,subscription3,subscription4,subscription5,subscription6));
        
        userService.addSubscription("wiebe.delvaux@gmail.com",Arrays.asList(subscription1));
        userService.addSubscription("john.doe@example.com",Arrays.asList(subscription2));
        userService.addSubscription("jane.smith@example.com",Arrays.asList(subscription3));
        userService.addSubscription("alice.johnson@example.com",Arrays.asList(subscription4));
        userService.addSubscription("bob.brown@example.com",Arrays.asList(subscription5));
        userService.addSubscription("charlie.davis@example.com",Arrays.asList(subscription6));
        
        // userRepository.saveAll(Arrays.asList(user1,user2,user3,user4,user5,user6));
    }

}
