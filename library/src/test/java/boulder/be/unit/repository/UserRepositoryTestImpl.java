package boulder.be.unit.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import boulder.be.model.User;
import boulder.be.repository.UserRepository;

public class UserRepositoryTestImpl implements UserRepository {

    public List<User> users;

    public UserRepositoryTestImpl() {
        users = new ArrayList<>();

        users.add(new User("Wiebe", "Delvaux", LocalDate.of(2004, 11, 24), "wiebe.delvaux@gmail.com", true));
        users.add(new User("John", "Doe", LocalDate.of(1990, 5, 15), "john.doe@example.com", false));
        users.add(new User("Jane", "Smith", LocalDate.of(1985, 8, 22), "jane.smith@example.com", false));
        users.add(new User("Alice", "Johnson", LocalDate.of(1995, 2, 10), "alice.johnson@example.com", false));
        users.add(new User("Bob", "Brown", LocalDate.of(2000, 7, 30), "bob.brown@example.com", true));
        users.add(new User("Charlie", "Davis", LocalDate.of(1998, 12, 5), "charlie.davis@example.com", true));

        users.add(new User("Eve", "Williams", LocalDate.of(2001, 4, 12), "eve.williams@example.com", true));
        users.add(new User("Frank", "Thomas", LocalDate.of(1992, 3, 18), "frank.thomas@example.com", false));
        users.add(new User("Grace", "Harris", LocalDate.of(1987, 6, 25), "grace.harris@example.com", false));
        users.add(new User("Henry", "Martinez", LocalDate.of(2003, 9, 2), "henry.martinez@example.com", true));
        users.add(new User("Ivy", "Clark", LocalDate.of(1996, 11, 17), "ivy.clark@example.com", false));
        users.add(new User("Jack", "Lewis", LocalDate.of(2002, 1, 29), "jack.lewis@example.com", true));
        users.add(new User("Jack", "Sparrow", LocalDate.of(1974, 1, 29), "jack.sparrow@example.com", false));
        users.add(new User("John", "Wick", LocalDate.of(1965, 4, 2), "john.wick@example.com", false));
        users.add(new User("Erik", "Laayer", LocalDate.of(2002, 9, 9), "erik.laayer@example.com", false));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public <S extends User> S save(S entity) {
        users.add(entity);
        return entity;
    }

    @Override
    public void delete(User entity) {
        users.remove(entity);
    }

    @Override
    public List<User> findByIsStudent(boolean isStudent) {
        for (User user : users) {
            if(user.getIsStudent()== true){
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public User findByEmail(String email) {
        return users.stream()
            .filter(user -> user.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        List<S> savedEntities = new ArrayList<>();
        for (S entity : entities) {
            users.add(entity);
            savedEntities.add(entity);
        }
        return savedEntities;
    }

    @Override
    public User findUserByFirstNameIgnoreCase(String first_name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserByFirstNameIgnoreCase'");
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllByIdInBatch'");
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllInBatch'");
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllInBatch'");
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flush'");
    }

    @Override
    public User getById(Long arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public User getOne(Long arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

    @Override
    public User getReferenceById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReferenceById'");
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAllAndFlush'");
    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAndFlush'");
    }

    @Override public

    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<User> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }
    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override

    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public Optional<User> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override

    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public List<User> findAll(Sort sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public User findUserByNameIgnoreCase(String last_name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserByNameIgnoreCase'");
    }

    @Override
     {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByEmail'");
    }

}