package com.moon.senla.educational_website.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedbacks")
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "detention", nullable = false)
    @Lob
    private String detention;

    @Column(name = "rank", nullable = false)
    @Min(1)
    @Max(5)
    private int rank;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Feedback)) {
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
