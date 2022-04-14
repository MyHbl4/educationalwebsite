package com.moon.senla.educational_website.model;

import java.io.Serializable;
import java.time.LocalDate;
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
public class Theory implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String description;

    private Topic topic;

    private User user;

    private LocalDate date = LocalDate.now();

    public Theory(String name, String description, Topic topic,
        User user) {
        this.name = name;
        this.description = description;
        this.topic = topic;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Theory theory = (Theory) o;
        return Objects.equals(getId(), theory.getId()) && Objects.equals(getName(),
            theory.getName()) && Objects.equals(getDescription(), theory.getDescription())
            && Objects.equals(getTopic(), theory.getTopic()) && Objects.equals(
            getUser(), theory.getUser()) && Objects.equals(getDate(), theory.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getTopic(), getUser(), getDate());
    }

    @Override
    public String toString() {
        return "Theory{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", topic=" + topic +
            ", user=" + user +
            ", date=" + date +
            '}';
    }
}
