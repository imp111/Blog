package blog.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    private Integer id;
    private String email;
    private String fullName;
    private String password;
    private Set<Role> roles;

    public User(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public User() {

    }

    @ManyToMany(fetch = FetchType.EAGER) // the fetch will be of type EAGER, we want the roles to be loaded together with the user
    @JoinTable(name = "user_roles") // this will create joining table for our Role-User relation and name it "user_roles"
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Id // @Id - tells Hibernate that this field will be primary key for the db
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue - makes the field generated automatically
    public Integer getId() {
        return this.id;
    }

    @Column(name = "email", unique = true, nullable = false) // @Column - defines a column name, and they are unique and non-nullable
    public String getEmail() {
        return this.email;
    }

    @Column(name = "fullName", nullable = false)
    public String getFullName() {
        return this.fullName;
    }

    @Column(name = "password", length = 20, nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
