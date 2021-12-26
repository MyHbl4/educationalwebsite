package com.moon.senla.educational_website.model;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "users")
@ToString(callSuper = true, exclude = "password")
public class User extends AbstractEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    @Size(max = 128)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    @Size(max = 128)
    private String username;

    @Column(name = "first_name", nullable = false)
    @Size(max = 128)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 128)
    private String lastName;

    @Column(name = "password", nullable = false)
    @Size(max = 128)
    private String password;

    @OneToMany
    private List<Course> courses;

    @OneToMany
    private List<Feedback> feedbacks;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @ManyToMany
    @JoinTable(name = "user_group",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;


}
