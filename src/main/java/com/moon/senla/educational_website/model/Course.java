package com.moon.senla.educational_website.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Group> groups;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks;

    @Column(name = "ranking")
    private int rankings = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    private Topic topic;

    public void addGroup(Group group) {
        groups.add(group);
        group.setCourse(this);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
        group.setCourse(null);
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        feedback.setCourse(this);
    }

    public void removeFeedback(Feedback feedback) {
        feedbacks.remove(feedback);
        feedback.setCourse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return getPrice() == course.getPrice() && getRankings() == course.getRankings()
            && Objects.equals(getName(), course.getName()) && Objects.equals(
            getUser(), course.getUser()) && Objects.equals(getGroups(), course.getGroups())
            && Objects.equals(getFeedbacks(), course.getFeedbacks())
            && Objects.equals(getTopic(), course.getTopic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getUser(), getGroups(), getFeedbacks(),
            getRankings(), getTopic());
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
            ", rankings=" + rankings +
            ", topic=" + topic +
            '}';
    }
}
