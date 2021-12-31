package com.moon.senla.educational_website.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Size(max = 128)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Theory> theories = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
        course.setTopic(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setTopic(null);
    }

    public void addTheory(Theory theory) {
        theories.add(theory);
        theory.setTopic(this);
    }

    public void removeTheory(Theory theory) {
        theories.remove(theory);
        theory.setTopic(null);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Topic)) {
            return false;
        }
        Topic topic = (Topic) o;
        return Objects.equals(getName(), topic.getName()) && Objects.equals(
            getCourses(), topic.getCourses()) && Objects.equals(getTheories(),
            topic.getTheories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCourses(), getTheories());
    }

    @Override
    public String toString() {
        return "Topic{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", courses=" + courses +
            ", theories=" + theories +
            '}';
    }
}
