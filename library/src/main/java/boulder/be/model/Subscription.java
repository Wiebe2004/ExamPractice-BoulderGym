package boulder.be.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    @NotBlank(message = "Type cannot be empty")
    private String type;

    @NotNull(message = "Start date is required!")
    @Future(message = "Start canot be in the future")
    private LocalDate startDate;

    private LocalDate endDate;

    protected Subscription() {
    }

    public Subscription(String type, LocalDate startDate) {
        setType(type);
        setStartDate(startDate);
        setEndDate(startDate);

    }

    private void setType(String type) {
        if (type.isEmpty()) {
            throw new DomainException("Membership type is required");
        }
        if (!type.equals("1MONTH") && !type.equals("3MONTH") && !type.equals("6MONTH")) {
            throw new DomainException("Invalid membership type");
        }
        this.type = type;
    }

    private void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    private void setEndDate(LocalDate startDate) {
        if (type.equals("1MONTH")) {
            this.endDate = startDate.plusMonths(1);
        }
        if (type.equals("3MONTH")) {
            this.endDate = startDate.plusMonths(3);
        }
        if (type.equals("6MONTH")) {
            this.endDate = startDate.plusMonths(6);
        }
    }

    public String getType() {
        return this.type;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

}
