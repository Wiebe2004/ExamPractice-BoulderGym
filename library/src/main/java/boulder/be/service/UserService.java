package boulder.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boulder.be.ServiceException;
import boulder.be.model.Subscription;
import boulder.be.model.User;
import boulder.be.repository.SubscriptionRepository;
import boulder.be.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public UserService(UserRepository userRepository,SubscriptionRepository subscriptionRepository){
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public List<User> getStudents(boolean isStudent) {
        List<User> students = userRepository.findByIsStudent(isStudent);
        if(students.isEmpty()){
            throw new ServiceException("No user students found");
        }
        return students;                            
    }

    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ServiceException("User does not exist.");
        }
        return user;
    }


    public User addSubscription(String email, List<Subscription> subscriptions){
        User user = findUserByEmail(email);
        if (user == null) {
            throw new ServiceException("User not found");
        }
        user.setSubscription(subscriptions);
        subscriptions.forEach(subscription -> subscription.setUser(user));
        subscriptionRepository.saveAll(subscriptions);
        return userRepository.save(user);
    }

}
