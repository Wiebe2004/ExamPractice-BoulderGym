package boulder.be.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TENTIMESPASS")
public class TenTimesPass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    @NotNull(message = "Start date is required!")
    // @Future(message = "Start canot be in the future")
    private LocalDate startDate;

    private LocalDate endDate;

    private String isActive;

    private int entries = 10;

    protected TenTimesPass() {
    }

    public TenTimesPass(LocalDate startDate) {
        setStartDate(startDate);
        this.startDate = startDate;
        // this.entries = entries;
    }

    private void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @PrePersist
    @PreUpdate
    private void calculateEndDateAndIsActiveAndEntries() {
        this.endDate = startDate.plusYears(1);
        if (endDate.isBefore(LocalDate.now())) {
            this.isActive = "EXPIRED";
        } else if (startDate.isAfter(LocalDate.now())) {
            this.isActive = "NOT ACTIVE: will be active from: " + startDate;
        } else {
            this.isActive = "TRUE";
        }
        // this.isActive = !endDate.isBefore(LocalDate.now());
        // this.entries = entries;
    }

    public String getIsActive() {
        return this.isActive;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public int getEntries() {
        return this.entries;
    }

    public Object setUser(User user) {
        return this.user = user;
    }

    public void removeEnty() {
        if (this.entries > 0) {
            this.entries--;
        } else {
            throw new DomainException("No entries left to remove");
            // List<TenTimesPass> tenTimes = tenTimesPassRepository.findByUser(user);
        }
    }
}
