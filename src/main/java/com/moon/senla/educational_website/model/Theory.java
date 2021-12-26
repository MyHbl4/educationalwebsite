package com.moon.senla.educational_website.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theory extends AbstractEntity {

    @Column(name = "name", nullable = false)
    @Size(max = 128)
    private String name;


    @Column(name = "description")
    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();
}
