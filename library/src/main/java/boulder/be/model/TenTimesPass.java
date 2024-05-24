package boulder.be.model;

import java.time.LocalDate;

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

    private boolean isActive;

    private int entries;

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
        this.isActive = !endDate.isBefore(LocalDate.now());
        this.entries = 10;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public int getEntries(){
        return this.entries;
    }

    public Object setUser(User user) {
        return this.user = user;
    }
}
