package com.moon.senla.educational_website.model;

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

    @Column(name = "name", nullable = false)
    @Size(max = 128)
    @NotBlank
    private String name;

    @Column(name = "price")
    @Min(0)
    @Max(100000)
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Group> groups = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();

    @Column(name = "rating")
    private int rating = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    private Topic topic;

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
