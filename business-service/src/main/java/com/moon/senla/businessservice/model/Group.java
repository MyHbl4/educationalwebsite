package com.moon.senla.businessservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Size(max = 128)
    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_group",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    @Column(name = "capacity", nullable = false)
    @Min(1)
    @Max(100)
    private int capacity;

    @Column(name = "available", nullable = false)
    @Min(0)
    @Max(100)
    private int available = capacity;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(getName(), group.getName()) && Objects.equals(
            getCourse(), group.getCourse()) && Objects.equals(getSchedules(),
            group.getSchedules()) && Objects.equals(getUsers(), group.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getCourse(), getSchedules(), getUsers());
    }

    @Override
    public String toString() {
        return "Group{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", course=" + course +
            ", schedules=" + schedules +
            ", users=" + users +
            '}';
    }
}
