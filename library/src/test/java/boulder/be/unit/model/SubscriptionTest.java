package boulder.be.unit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import boulder.be.model.DomainException;
import boulder.be.model.Subscription;
import boulder.be.model.TenTimesPass;
// import boulder.be.model.TenTimesPass;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class SubscriptionTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void givenValidValues_whenCreatingSubscriptionWithActiveStatus_thenActiveSubscriptionIsCreated() {
        LocalDate startDate = LocalDate.of(2024, 5, 27);

        Subscription pass = new Subscription("6MONTH", startDate);

        assertEquals(startDate, pass.getStartDate());
        assertEquals(startDate.plusMonths(6), pass.getEndDate());
        assertEquals("TRUE", pass.getIsActive());
    }

    @Test
    public void givenValidValues_whenCreatingSubscriptionWithNotActive_thenNotActiveSubscriptionIsCreated() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.plusMonths(3);

        Subscription pass = new Subscription("6MONTH", startDate);

        assertEquals(startDate, pass.getStartDate());
        assertEquals(startDate.plusMonths(6), pass.getEndDate());
        assertEquals("NOT ACTIVE: will be active from: " + startDate, pass.getIsActive());
    }

    @Test
    public void givenExpiredSubscription_whenGetIsActive_thenStatusIsExpired() {
        // LocalDate today = LocalDate.now();
        LocalDate startDate = LocalDate.of(2023, 11, 20);

        Subscription pass = new Subscription("6MONTH", startDate);

        assertEquals(startDate, pass.getStartDate());
        assertEquals(startDate.plusMonths(6), pass.getEndDate());
        assertEquals("EXPIRED", pass.getIsActive());
    }

    @Test
    public void givenType6MONTH_whenSubscriptionIsMade_endDateIsPlus6Months() {
        // LocalDate today = LocalDate.now();
        LocalDate startDate = LocalDate.now();

        Subscription pass = new Subscription("6MONTH", startDate);

        assertEquals(startDate, pass.getStartDate());
        assertEquals(startDate.plusMonths(6), pass.getEndDate(), "EndDate is 6 months later then startDate");
        assertEquals("6MONTH", pass.getType(), "Type is = to 6MONTH");

    }

    @Test
    public void givenType3MONTH_whenSubscriptionIsMade_endDateIsPlus3Months() {
        // LocalDate today = LocalDate.now();
        LocalDate startDate = LocalDate.now();

        Subscription pass = new Subscription("3MONTH", startDate);

        assertEquals(startDate, pass.getStartDate());
        assertEquals(startDate.plusMonths(3), pass.getEndDate(), "EndDate is 3 months later then startDate");
        assertEquals("3MONTH", pass.getType(), "Type is = to 3MONTH");

    }

    @Test
    public void givenType1MONTH_whenSubscriptionIsMade_endDateIsPlus1Months() {
        // LocalDate today = LocalDate.now();
        LocalDate startDate = LocalDate.now();

        Subscription pass = new Subscription("1MONTH", startDate);

        assertEquals(startDate, pass.getStartDate());
        assertEquals(startDate.plusMonths(1), pass.getEndDate(), "EndDate is 3 months later then startDate");
        assertEquals("1MONTH", pass.getType(), "Type is = to 1MONTH");
    }

    @Test
    public void givenInvalidType_whenSubscriptionIsCreated_domainExceptionIsThrown() {
        LocalDate startDate = LocalDate.now();

        assertThrows(DomainException.class, () -> {
            new Subscription("2MONTH", startDate);
        });
    }
}
