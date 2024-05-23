package boulder.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boulder.be.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIsStudent(boolean isStudent);
    
}