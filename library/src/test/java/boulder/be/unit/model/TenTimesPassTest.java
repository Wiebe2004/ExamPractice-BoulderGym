package boulder.be.unit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import boulder.be.model.DomainException;
import boulder.be.model.TenTimesPass;
import boulder.be.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TenTimesPassTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void givenValidValues_whenCreatingTenTimesPassWithActiveStatus_thenActiveTenTimesPassIsCreated() {
        LocalDate startDate = LocalDate.of(2024, 5, 27);

        TenTimesPass pass = new TenTimesPass(startDate);

        assertEquals(startDate, pass.getStartDate());
        assertEquals(startDate.plusYears(1), pass.getEndDate());
        assertEquals("TRUE", pass.getIsActive());
        assertEquals(10, pass.getEntries());
    }

    @Test
    public void givenValidValues_whenCreatingTenTimesPassWithNotActive_thenNotActiveTenTimesPassIsCreated() {
        // Arrange
        LocalDate today = LocalDate.now();
        LocalDate NotActiveDate = today.plusMonths(1);

        // Act
        TenTimesPass pass = new TenTimesPass(NotActiveDate);

        // Assert
        assertEquals(NotActiveDate, pass.getStartDate(), "Start date should match");
        assertEquals(NotActiveDate.plusYears(1), pass.getEndDate(), "End date should be 1 year after start date");
        assertEquals("NOT ACTIVE: will be active from: " + NotActiveDate, pass.getIsActive(),
                "Pass should be active from: " + NotActiveDate);
        assertEquals(10, pass.getEntries(), "Entries should be initialized to 10");
    }

    @Test
    public void givenExpiredTenTimesPass_whenGetIsActive_thenStatusIsExpired() {
        // Test retrieving the isActive status for an expired TenTimesPass
        LocalDate startDate = LocalDate.of(2023, 5, 20);
        // LocalDate NotActiveDate = date.plusYears(1);

        // Act
        TenTimesPass pass = new TenTimesPass(startDate);
        int entries = pass.getEntries();

        // Assert
        assertEquals(startDate, pass.getStartDate(), "Start date should match");
        assertEquals(startDate.plusYears(1), pass.getEndDate(), "End date should be 1 year after start date");
        assertEquals("EXPIRED", pass.getIsActive(), "Pass should be expired");
        assertEquals(entries, pass.getEntries(), "Entries left over from pass");

    }

    @Test
    public void Given10TimesPass_whereEntriesAreEmpty_DomainExceptionIsThrown() {
        LocalDate startDate = LocalDate.of(2024, 5, 27);
        TenTimesPass pass = new TenTimesPass(startDate);

        for (int i = 0; i < 10; i++) {
            pass.removeEntry();
        }

        assertThrows(DomainException.class, () -> {
            pass.removeEntry();
        });
    }

    @Test
    public void givenStartDateEmpty_whenCreatingTentimesPass_exceptionIsThorwn() {
        TenTimesPass pass = new TenTimesPass(null);

        Set<ConstraintViolation<TenTimesPass>> violations = validator.validate(pass);

        assertEquals(1, violations.size());
        assertEquals("Start date is required!", violations.iterator().next().getMessage());

    }

    @Test
    public void givenValidValues_whenSettingTenTimesPass_valuesAreSet() {
        LocalDate startDate = LocalDate.of(2024, 5, 27);
        TenTimesPass pass = new TenTimesPass(startDate);

        assertNotNull(pass.getStartDate());
        assertEquals(startDate, pass.getStartDate());
        assertNotNull(pass.getEndDate());
        assertEquals(startDate.plusYears(1), pass.getEndDate());
        assertEquals("TRUE", pass.getIsActive());
        assertEquals(10, pass.getEntries());
    }

}
