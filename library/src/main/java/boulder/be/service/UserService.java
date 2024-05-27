package boulder.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boulder.be.ServiceException;
import boulder.be.model.Subscription;
import boulder.be.model.TenTimesPass;
import boulder.be.model.User;
import boulder.be.repository.SubscriptionRepository;
import boulder.be.repository.TenTimesPassRepository;
import boulder.be.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserService {
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
    private TenTimesPassRepository tenTimesPassRepository;

    @Autowired
    public UserService(UserRepository userRepository, SubscriptionRepository subscriptionRepository,
            TenTimesPassRepository tenTimesPassRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.tenTimesPassRepository = tenTimesPassRepository;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public List<User> getStudents(boolean isStudent) {
        List<User> students = userRepository.findByIsStudent(isStudent);
        if (students.isEmpty()) {
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

    public User addSubscription(String email, List<Subscription> subscriptions) {
        User user = findUserByEmail(email);
        if (user == null) {
            throw new ServiceException("User not found");
        }

        List<TenTimesPass> pass = tenTimesPassRepository.findByUser(user);

        if (!pass.isEmpty()) {
            throw new ServiceException("User already has a 10 times pass, you cant have both.");
        }

        user.setSubscription(subscriptions);
        subscriptions.forEach(subscription -> subscription.setUser(user));
        subscriptionRepository.saveAll(subscriptions);
        return userRepository.save(user);
    }

    // public User updateSubscription(String email, @Valid List<Subscription>
    // NewSubscriptionInfo) {
    // User user = userRepository.findByEmail(email);

    // if (user == null) {
    // throw new ServiceException("User not found");
    // }

    // user.updateUser(NewSubscriptionInfo.getFirstName(),NewSubscriptionInfo.getName(),NewSubscriptionInfo.getBirthDate()
    // ,newInformation.getEmail() ,newInformation.getIsStudent());
    // return userRepository.save(user);
    // }

    public User addTenTimesPass(String email, List<TenTimesPass> tenTimes) {
        User user = findUserByEmail(email);
        if (user == null) {
            throw new ServiceException("User not found");
        }
        List<Subscription> subscriptions = subscriptionRepository.findByUser(user);

        if (!subscriptions.isEmpty()) {
            throw new ServiceException("User already has a subscription, you cant have both.");
        }
        user.setTenTimesPass(tenTimes);
        tenTimes.forEach(tenTimesPass -> tenTimesPass.setUser(user));
        tenTimesPassRepository.saveAll(tenTimes);
        return userRepository.save(user);
    }

    public User findUserByFirstName(String first_name) {
        User user = userRepository.findUserByFirstNameIgnoreCase(first_name);
        if (user == null) {
            throw new ServiceException("User not found first name: " + first_name);
        }
        return user;
    }

    public User findUserByLastName(String last_name) {
        User user = userRepository.findUserByNameIgnoreCase(last_name);
        if (user == null) {
            throw new ServiceException("User not found first name: " + last_name);
        }
        return user;
    }

    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ServiceException("User already exists");
        }

        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ServiceException("Cannot delete user with email: " + email + "Because user was not found.");
        }
        userRepository.delete(user);
    }

    @Transactional
    public void deleteUserSubscription(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ServiceException(
                    "Cannot delete subscription from user with email: " + email + " because user was not found.");
        }

        List<Subscription> subscriptions = subscriptionRepository.findByUser(user);

        if (subscriptions.isEmpty()) {
            throw new ServiceException("User has no subscription.");
        }

        subscriptionRepository.deleteByUser(user); // This line deletes subscriptions, not user

    }

    public User scanUser(String email) {
        User user = userRepository.findByEmail(email);

        List<Subscription> subscriptions = subscriptionRepository.findByUser(user);
        List<TenTimesPass> tenTimes = tenTimesPassRepository.findByUser(user);
        if (tenTimes.isEmpty() && subscriptions.isEmpty()) {
            throw new ServiceException("No subscription or 10 times pass was found with user: " + email);
        } else if (!tenTimes.isEmpty()) {
            TenTimesPass pass = tenTimes.get(0);
            pass.removeEntry();
            tenTimesPassRepository.save(pass);
            if (pass.getEntries() == 0) {
                tenTimesPassRepository.delete(pass);
                throw new ServiceException("10 times pass is empty");
            }
        } else if (!subscriptions.isEmpty()) {
            return user;
        }
        return user;
    }

    @Transactional
    public void deleteUserTenTimesPass(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ServiceException(
                    "Cannot delete ten times pass from user with email: " + email + " because user was not found.");
        }

        List<TenTimesPass> pass = tenTimesPassRepository.findByUser(user);

        if (pass.isEmpty()) {
            throw new ServiceException("User has no ten times pass.");
        }

        tenTimesPassRepository.deleteByUser(user); // This line deletes subscriptions, not user

    }

    public User updateUser(String email, User newInformation) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ServiceException("User not found");
        }

        user.updateUser(newInformation.getFirstName(), newInformation.getName(), newInformation.getBirthDate(),
                newInformation.getEmail(), newInformation.getIsStudent());
        return userRepository.save(user);
    }

}
