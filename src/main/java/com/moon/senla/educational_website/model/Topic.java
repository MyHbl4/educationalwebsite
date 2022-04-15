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
public class Topic implements Serializable {

    @Id
    private String id;


    @Indexed
    private String name;

    @DBRef
    private List<Course> courses = new ArrayList<>();

    @DBRef
    private List<Theory> theories = new ArrayList<>();

    public Topic(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
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
