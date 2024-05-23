package boulder.be.restcontroller;

import org.springframework.web.bind.annotation.RestController;

import boulder.be.model.User;
import boulder.be.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> allUsers(){
        return userService.allUsers();
    }

    @GetMapping("/students")
    public List<User> getAllStudents() {
        return userService.getStudents(true);
    }
    

}
