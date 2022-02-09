package com.moon.senla.educational_website.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank
    @Size(max = 128)
    private String username;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(max = 128)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(max = 128)
    private String lastName;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 256)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Theory> theories = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_group",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups = new ArrayList<>();

    public void setEmail(String email) {
        this.email = ObjectUtils.isEmpty(email) ? "" : email.toLowerCase();
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.setUser(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setUser(null);
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        feedback.setUser(this);
    }

    public void removeFeedback(Feedback feedback) {
        feedbacks.remove(feedback);
        feedback.setUser(null);
    }

    public void addTheory(Theory theory) {
        theories.add(theory);
        theory.setUser(this);
    }

    public void removeTheory(Theory theory) {
        theories.remove(theory);
        theory.setUser(null);
    }

    public void addGroup(Group group) {
        groups.add(group);
        group.addUser(this);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
        group.removeUser(this);
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getEmail(),
            user.getEmail()) && Objects.equals(getUsername(), user.getUsername())
            && Objects.equals(getFirstName(), user.getFirstName())
            && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(
            getPassword(), user.getPassword()) && Objects.equals(getCourses(),
            user.getCourses()) && Objects.equals(getFeedbacks(), user.getFeedbacks())
            && Objects.equals(getTheories(), user.getTheories()) && Objects.equals(
            getRoles(), user.getRoles()) && Objects.equals(getGroups(), user.getGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getUsername(), getFirstName(), getLastName(),
            getPassword(), getCourses(), getFeedbacks(), getTheories(), getRoles(), getGroups());
    }

}
