package com.moon.senla.educational_website.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.ObjectUtils;

@Document
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id;

    @Indexed
    private String email;

    @Indexed
    private String username;

    private String firstName;

    private String lastName;

    private String password;

    @DBRef
    private List<Course> courses = new ArrayList<>();

    @DBRef
    private List<Feedback> feedbacks = new ArrayList<>();

    @DBRef
    private List<Theory> theories = new ArrayList<>();

    private List<Role> roles;

    @DBRef
    private List<Group> groups = new ArrayList<>();

    private Status status = Status.ACTIVE;

    public User(String email, String username, String firstName, String lastName,
        String password, List<Role> roles) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = ObjectUtils.isEmpty(email) ? "" : email.toLowerCase();
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
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

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", username='" + username + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", password='" + password + '\'' +
            ", courses=" + courses +
            ", feedbacks=" + feedbacks +
            ", theories=" + theories +
            ", roles=" + roles +
            ", groups=" + groups +
            "} " + super.toString();
    }
}
