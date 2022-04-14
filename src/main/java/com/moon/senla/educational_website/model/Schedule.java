package com.moon.senla.educational_website.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Schedule implements Serializable {

    @Id
    private String id;

    private LocalDateTime date;

    private Group group;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        return Objects.equals(getId(), schedule.getId()) && Objects.equals(
            getDate(), schedule.getDate()) && Objects.equals(getGroup(), schedule.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getGroup());
    }

    @Override
    public String toString() {
        return "Schedule{" +
            "id=" + id +
            ", date=" + date +
            ", group=" + group +
            '}';
    }
}
