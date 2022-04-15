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

@Document
@Getter
@Setter
@NoArgsConstructor
public class Group implements Serializable {

    @Id
    private String id;

    @Indexed
    private String name;

    private Course course;

    @DBRef
    private List<Schedule> schedules = new ArrayList<>();

    @DBRef
    private List<User> users = new ArrayList<>();

    private int capacity;

    private int available = capacity;

    public Group(String name, Course course, int capacity) {
        this.name = name;
        this.course = course;
        this.capacity = capacity;
    }

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
