package com.moon.senla.educational_website.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "theories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Size(max = 128)
    private String name;

    @Column(name = "description", nullable = false)
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Topic topic;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Theory)) {
            return false;
        }
        Theory theory = (Theory) o;
        return Objects.equals(getName(), theory.getName()) && Objects.equals(
            getDescription(), theory.getDescription()) && Objects.equals(getTopic(),
            theory.getTopic()) && Objects.equals(getDate(), theory.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getTopic(), getDate());
    }

    @Override
    public String toString() {
        return "Theory{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", topic=" + topic +
            ", date=" + date +
            '}';
    }
}
