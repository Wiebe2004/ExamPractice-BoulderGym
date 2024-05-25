package boulder.be.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Subscription> subscription;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<TenTimesPass> tenTimesPass;

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private long id;

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "boulder.be.util.RandomIdGenerator")
    private String id;

    @NotBlank(message = "First name may not be empty")
    private String firstName;

    @NotBlank(message = "Name may not be empty")
    private String name;

    @NotBlank(message = "Email may not be empty")
    private String email;

    @NotNull(message = "BirthDate cannot be empty")
    private LocalDate birthDate;

    private boolean isStudent;

    private int age;

    protected User() {
    }

    public User(String firstName, String name, LocalDate birthDate, String email, boolean isStudent) {
        setFirstName(firstName);
        setName(name);
        setBirthDate(birthDate);
        setEmail(email);
        setStudent(isStudent);
        this.setAge();
        setSubscription(subscription);
        setTenTimesPass(tenTimesPass);
    }

    public void setSubscription(List<Subscription> subscription) {
        this.subscription = subscription;
    }

    public void setTenTimesPass(List<TenTimesPass> tenTimesPass) {
        this.tenTimesPass = tenTimesPass;
    }

    public List<Subscription> getSubscription() {
        return subscription;
    }

    public List<TenTimesPass> getTenTimesPass() {
        return tenTimesPass;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getName() {
        return this.name;
    }
    
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    
    @PrePersist
    @PreUpdate
    public void setAge() {
        this.age = Period.between(this.birthDate, LocalDate.now()).getYears();
        setStudent(this.isStudent);  // Re-evaluate student status when age is set
    }
    
    @PostLoad
    private void calculateAgeAfterLoad() {
        this.age = Period.between(this.birthDate, LocalDate.now()).getYears();
    }
    
    public int getAge() {
        return this.age;
    }
    private void setStudent(boolean isStudent) {
        this.isStudent = (this.age <= 24) && isStudent;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean getIsStudent() {
        return this.isStudent;
    }

    public String getId() {
        return id;
    }
}
