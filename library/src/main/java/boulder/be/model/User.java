package boulder.be.model;

import java.time.LocalDate;
import java.time.Period;

import org.hibernate.mapping.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Subscription> subscription;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
        setAge(birthDate);
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

    private void setStudent(boolean isStudent) {
        this.isStudent = isStudent;
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

    public void setAge(LocalDate birthDate){
        this.age = Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public int getAge() {
        return this.age;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean getIsStudent() {
        return this.isStudent;
    }

    public Long getID() {
        return id;
    }
}
