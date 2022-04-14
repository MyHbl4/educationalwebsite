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
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Course implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private int price;

    private User user;

    private List<Group> groups = new ArrayList<>();

    private List<Feedback> feedbacks = new ArrayList<>();

    private int rating = 0;

    private Topic topic;

    public Course(String name, int price, User user,
        Topic topic) {
        this.name = name;
        this.price = price;
        this.user = user;
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return getPrice() == course.getPrice() && getRating() == course.getRating()
            && Objects.equals(getName(), course.getName()) && Objects.equals(
            getUser(), course.getUser()) && Objects.equals(getGroups(), course.getGroups())
            && Objects.equals(getFeedbacks(), course.getFeedbacks())
            && Objects.equals(getTopic(), course.getTopic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getUser(), getGroups(), getFeedbacks(),
            getRating(), getTopic());
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", user=" + user +
            ", groups=" + groups +
            ", feedbacks=" + feedbacks +
            ", rating=" + rating +
            ", topic=" + topic +
            '}';
    }
}
