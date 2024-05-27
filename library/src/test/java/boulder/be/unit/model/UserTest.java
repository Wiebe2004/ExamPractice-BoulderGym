package boulder.be.unit.model;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Set;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validator;
import boulder.be.model.DomainException;
import boulder.be.model.Subscription;
import boulder.be.model.TenTimesPass;
import boulder.be.model.User;

public class UserTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void givenValidUser_whenSetSubscription_thenSubscriptionIsSet() {
        // Test if setting subscription for a valid user is correctly performed
        LocalDate startDate = LocalDate.of(2024, 05, 27);
        User user = new User("Alex", "Dam", LocalDate.of(1990, 05, 22), "alex.dam@example.com", false);
        Subscription subscription = new Subscription("6MONTH", startDate);

        List<Subscription> subscriptions = List.of(subscription);
        user.setSubscription(subscriptions);

        assertEquals(1, user.getSubscription().size(), "User should have 1 subscription");
    }

    @Test
    public void givenValidUser_whenSetTenTimesPass_thenTenTimesPassIsSet() {
        // Test if setting ten times pass for a valid user is correctly performed
        // LocalDate startDate = LocalDate.of(2024, 05, 27);
        User user = new User("Alex", "Dam", LocalDate.of(1990, 05, 22), "alex.dam@example.com", false);
        TenTimesPass pass = new TenTimesPass(LocalDate.of(2024, 05, 27));

        List<TenTimesPass> passes = List.of(pass);
        user.setTenTimesPass(passes);
        assertEquals(1, user.getTenTimesPass().size(), "User has one subscription");
    }

    @Test
    public void givenValidValues_whenCreatingUser_thenUserIsCreatedWithThoseValues() {
        User user = new User("Alex", "Dam", LocalDate.of(1990, 05, 22), "alex.dam@example.com", false);
        assertEquals("Alex", user.getFirstName());
        assertEquals("Dam", user.getName());
        assertEquals(LocalDate.of(1990, 05, 22), user.getBirthDate());
        assertEquals(34, user.getAge());
        assertEquals("alex.dam@example.com", user.getEmail());
        assertEquals(false, user.getIsStudent());
    }

    @Test
    public void givenFirstNameEmpty_whenCreatingUser_thenExceptionIsThrown() {
        User user = new User(null, "Dam", LocalDate.of(1990, 05, 22), "alex.dam@example.com", false);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        assertEquals("First name may not be empty", violations.iterator().next().getMessage());

    }

    @Test
    public void givenLastNameEmpty_whenCreatingUser_thenExceptionIsThrown() {
        User user = new User("Alex", null, LocalDate.of(1990, 05, 22), "alex.dam@example.com", false);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        assertEquals("Name may not be empty", violations.iterator().next().getMessage());

    }

    @Test
    public void givenBirthDateEmpty_whenCreatingUser_thenExceptionIsThrown() {
        User user = new User("Alex", "Dam", null, "alex.dam@example.com", false);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        assertEquals("BirthDate cannot be empty", violations.iterator().next().getMessage());
    }

    @Test
    public void givenEmailEmpty_whenCreatingUser_thenExceptionIsThrown() {
        User user = new User("Alex", "Dam", LocalDate.of(1990, 05, 22), null, false);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        assertEquals("Email may not be empty", violations.iterator().next().getMessage());
    }

    @Test
    public void givenBadFormatEmail_whenCreatingUser_thenExceptionIsThrown() {
        User user = new User("Alex", "Dam", LocalDate.of(1990, 05, 22), "jan.dam", false);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        assertEquals("Email must be in a correct format", violations.iterator().next().getMessage());
    }

    @Test
    public void givenAdultUser_whenCreatingWithoutSubscription_thenUserIsCreated() {
        User user = new User("Alex", "Dam", LocalDate.of(1990, 05, 22), "alex.dam@example.com", false);
        assertEquals("Alex", user.getFirstName());
        assertEquals("Dam", user.getName());
        assertEquals(LocalDate.of(1990, 05, 22), user.getBirthDate());
        assertEquals(34, user.getAge());
        assertEquals("alex.dam@example.com", user.getEmail());
        assertEquals(false, user.getIsStudent());
        assertNull(user.getSubscription());
    }

    @Test
    public void givenAdultUser_whenCreatingWithoutTenTimesPass_thenUserIsCreated() {
        User user = new User("Alex", "Dam", LocalDate.of(1990, 05, 22), "alex.dam@example.com", false);
        assertEquals("Alex", user.getFirstName());
        assertEquals("Dam", user.getName());
        assertEquals(LocalDate.of(1990, 05, 22), user.getBirthDate());
        assertEquals(34, user.getAge());
        assertEquals("alex.dam@example.com", user.getEmail());
        assertEquals(false, user.getIsStudent());
        assertNull(user.getTenTimesPass());
    }

    @Test
    public void givenUserWithAgeOver24_whenSetStudent_thenStudentIsFalse() {
        User user = new User("Alex", "Dam", LocalDate.of(1998, 05, 22), "alex.dam@example.com", true);
        assertEquals("Alex", user.getFirstName());
        assertEquals("Dam", user.getName());
        assertEquals(LocalDate.of(1998, 05, 22), user.getBirthDate());
        assertEquals(26, user.getAge());
        assertEquals("alex.dam@example.com", user.getEmail());
        assertEquals(false, user.getIsStudent(), "Even though its set as  true in User, it is false because user is above 24yo"); 
        // assertNull(user.getTenTimesPass());
    }

    @Test
    public void givenValidUser_whenUpdateUser_thenUserIsUpdated() {
        User user = new User("Alex", "Dam", LocalDate.of(1998, 05, 22), "alex.dam@example.com", true);
        user.updateUser("Aleks","Daem", LocalDate.of(1998, 04, 22), "aleks.daem@example.com", false);
        
        assertEquals("Aleks", user.getFirstName());
        assertEquals("Daem", user.getName());
        assertEquals(LocalDate.of(1998, 04, 22), user.getBirthDate());
        assertEquals(26, user.getAge());
        assertEquals("aleks.daem@example.com", user.getEmail());
        assertEquals(false, user.getIsStudent());
    
    }   

    @Test
    public void givenValidUser_whenUpdateUser_thenStudentStatusIsReevaluated() {
        User user = new User("Alex", "Dam", LocalDate.of(1998, 05, 22), "alex.dam@example.com", false);
        user.updateUser("Aleks","Daem", LocalDate.of(2001, 04, 22), "aleks.daem@example.com", true);
        
        assertEquals("Aleks", user.getFirstName());
        assertEquals("Daem", user.getName());
        assertEquals(LocalDate.of(2001, 04, 22), user.getBirthDate());
        assertEquals(23, user.getAge());
        assertEquals("aleks.daem@example.com", user.getEmail());
        assertEquals(true, user.getIsStudent());
    }
}
