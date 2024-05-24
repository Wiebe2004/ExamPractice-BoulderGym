package boulder.be.restcontroller;

import org.springframework.web.bind.annotation.RestController;

import boulder.be.ServiceException;
import boulder.be.model.Subscription;
import boulder.be.model.User;
import boulder.be.service.UserService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private UserService userService;
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @GetMapping("/students")
    public List<User> getAllStudents() {
        return userService.getStudents(true);
    }

    @GetMapping("/{email}")
    public User getUsersByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/search")
    public User getUsersByFirstOrLastName(@RequestParam(value = "first_name", required = false) String first_name,
            @RequestParam(value = "last_name", required = false) String last_name) {
        if (first_name == null) {
            return userService.findUserByLastName(last_name);
        } else {
            return userService.findUserByFirstName(first_name);
        }
    }

    @PostMapping()
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/{email}/subscription")
    public User addSubscription(@PathVariable String email, @Valid @RequestBody List<Subscription> subscription) {
        return userService.addSubscription(email, subscription);
    }

    @DeleteMapping("/{email}")
    public Map<String, String> deleteUser(@PathVariable String email){
        userService.deleteUser(email);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User with email: " + email + " has been deleted.");
        return response;
    }
    @DeleteMapping("/{email}/subscription")
    public Map<String, String> deleteSubscription(@PathVariable String email){
        userService.deleteUserSubscription(email);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Subscription from user with email: " + email + " has been deleted.");
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ IllegalArgumentException.class })
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Illegal Argument", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ ServiceException.class })
    public Map<String, String> handleServiceException(ServiceException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Service Exception", ex.getMessage());
        return errors;
    }
}
