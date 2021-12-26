package com.moon.senla.educational_website.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "topics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic extends AbstractEntity {

    @Column(name = "name", nullable = false)
    @Size(max = 128)
    private String name;

    @OneToMany
    private List<Course> courses;

    @OneToMany
    private List<Theory> theories;
}
