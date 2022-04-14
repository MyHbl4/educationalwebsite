package com.moon.senla.educational_website.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Feedback implements Serializable {

    @Id
    private String id;

    private Course course;

    private User user;

    private String detention;

    private int rank;

    private LocalDate date = LocalDate.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Feedback feedback = (Feedback) o;
        return getRank() == feedback.getRank() && Objects.equals(getCourse(),
            feedback.getCourse()) && Objects.equals(getUser(), feedback.getUser())
            && Objects.equals(getDetention(), feedback.getDetention())
            && Objects.equals(getDate(), feedback.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourse(), getUser(), getDetention(), getRank(), getDate());
    }

    @Override
    public String toString() {
        return "Feedback{" +
            "id=" + id +
            ", course=" + course +
            ", user=" + user +
            ", detention='" + detention + '\'' +
            ", rank=" + rank +
            ", date=" + date +
            '}';
    }
}
