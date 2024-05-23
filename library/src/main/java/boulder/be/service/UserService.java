package boulder.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boulder.be.ServiceException;
import boulder.be.model.User;
import boulder.be.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
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


}
