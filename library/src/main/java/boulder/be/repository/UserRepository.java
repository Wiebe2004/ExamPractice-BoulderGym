package boulder.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boulder.be.model.User;
// import boulder.be.unit.repository.UserRepositoryTestImpl;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    List<User> findByIsStudent(boolean isStudent);

    User findByEmail(String email);

    User findUserByFirstNameIgnoreCase(String first_name);

    User findUserByNameIgnoreCase(String last_name);

    boolean existsByEmail(String email);
}
