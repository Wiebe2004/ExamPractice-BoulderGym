package boulder.be.repository;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import boulder.be.model.Subscription;
import boulder.be.model.TenTimesPass;
import boulder.be.model.User;
import boulder.be.service.UserService;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
    private TenTimesPassRepository tenTimesPassRepository;
    private UserService userService;

    @Autowired
    public DbInitializer(UserRepository userRepository, SubscriptionRepository subscriptionRepository,TenTimesPassRepository tenTimesPassRepository, UserService userService) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.tenTimesPassRepository = tenTimesPassRepository;
        this.userService = userService;

    }

    @PostConstruct
    public void initialize() {
        User user1 = new User("Wiebe", "Delvaux", LocalDate.of(2004, 11, 24), "wiebe.delvaux@gmail.com", true);
        User user2 = new User("John", "Doe", LocalDate.of(1990, 5, 15), "john.doe@example.com", false);
        User user3 = new User("Jane", "Smith", LocalDate.of(1985, 8, 22), "jane.smith@example.com", false);
        User user4 = new User("Alice", "Johnson", LocalDate.of(1995, 2, 10), "alice.johnson@example.com", false);
        User user5 = new User("Bob", "Brown", LocalDate.of(2000, 7, 30), "bob.brown@example.com", true);
        User user6 = new User("Charlie", "Davis", LocalDate.of(1998, 12, 5), "charlie.davis@example.com", true);

        User user7 = new User("Eve", "Williams", LocalDate.of(2001, 4, 12), "eve.williams@example.com", true);
        User user8 = new User("Frank", "Thomas", LocalDate.of(1992, 3, 18), "frank.thomas@example.com", false);
        User user9 = new User("Grace", "Harris", LocalDate.of(1987, 6, 25), "grace.harris@example.com", false);
        User user10 = new User("Henry", "Martinez", LocalDate.of(2003, 9, 2), "henry.martinez@example.com", true);
        User user11 = new User("Ivy", "Clark", LocalDate.of(1996, 11, 17), "ivy.clark@example.com", false);
        User user12 = new User("Jack", "Lewis", LocalDate.of(2002, 1, 29), "jack.lewis@example.com", true);
        User user13 = new User("Jack", "Sparrow", LocalDate.of(1974, 1, 29), "jack.sparrow@example.com", false);
        User user14 = new User("John", "Wick", LocalDate.of(1965, 4, 2), "john.wick@example.com", false);
        User user15 = new User("Erik", "Laayer", LocalDate.of(2002, 9, 9), "erik.laayer@example.com", false);

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6,user7,user8,user9,user10,user11,user12,user14,user15,user13));

        Subscription subscription1 = new Subscription("1MONTH", LocalDate.of(2024, 5, 23));
        Subscription subscription2 = new Subscription("3MONTH", LocalDate.of(2024, 1, 20));
        Subscription subscription3 = new Subscription("6MONTH", LocalDate.of(2024, 3, 4));
        Subscription subscription4 = new Subscription("6MONTH", LocalDate.of(2024, 5, 13));
        Subscription subscription5 = new Subscription("3MONTH", LocalDate.of(2024, 2, 5));
        Subscription subscription6 = new Subscription("6MONTH", LocalDate.of(2023, 2, 2));

        subscriptionRepository.saveAll(Arrays.asList(subscription1, subscription2, subscription3, subscription4,subscription5, subscription6));

        userService.addSubscription("wiebe.delvaux@gmail.com", Arrays.asList(subscription1));
        userService.addSubscription("john.doe@example.com", Arrays.asList(subscription2));
        userService.addSubscription("jane.smith@example.com", Arrays.asList(subscription3));
        userService.addSubscription("alice.johnson@example.com", Arrays.asList(subscription4));
        userService.addSubscription("bob.brown@example.com", Arrays.asList(subscription5));
        userService.addSubscription("charlie.davis@example.com", Arrays.asList(subscription6));

        TenTimesPass tenTimes1 = new TenTimesPass(LocalDate.of(2024, 05, 20));
        TenTimesPass tenTimes2 = new TenTimesPass(LocalDate.of(2023, 05, 10));
        TenTimesPass tenTimes3 = new TenTimesPass(LocalDate.of(2024, 01, 2));
        TenTimesPass tenTimes4 = new TenTimesPass(LocalDate.of(2024, 03, 13));
        TenTimesPass tenTimes5 = new TenTimesPass(LocalDate.of(2023, 11, 24));
        TenTimesPass tenTimes6 = new TenTimesPass(LocalDate.of(2024, 06, 07));
        
        tenTimesPassRepository.saveAll(Arrays.asList(tenTimes1, tenTimes2, tenTimes3, tenTimes4, tenTimes5, tenTimes6));

        userService.addTenTimesPass("eve.williams@example.com", Arrays.asList(tenTimes1));
        userService.addTenTimesPass("frank.thomas@example.com", Arrays.asList(tenTimes2));
        userService.addTenTimesPass("grace.harris@example.com", Arrays.asList(tenTimes3));
        userService.addTenTimesPass("henry.martinez@example.com", Arrays.asList(tenTimes4));
        userService.addTenTimesPass("ivy.clark@example.com", Arrays.asList(tenTimes5));
        userService.addTenTimesPass("jack.lewis@example.com", Arrays.asList(tenTimes6));

        
        // userRepository.saveAll(Arrays.asList(user1,user2,user3,user4,user5,user6));
    }

}
