package com.moon.senla.educational_website.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedbacks")
public class Feedback extends AbstractEntity {

    @ManyToOne
    private Course course;

    @ManyToOne
    private User user;

    @Column(name = "detention")
    @Lob
    private String detention;

    @Column(name = "rank")
    @Min(1)
    @Max(5)
    private int rank;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

}
