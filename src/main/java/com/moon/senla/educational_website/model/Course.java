package com.moon.senla.educational_website.model;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany
    @CollectionTable(name = "course_groups", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "group")
    private List<Group> groups;

    @OneToMany
    @CollectionTable(name = "course_feedbacks", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "feedback")
    private List<Feedback> feedbacks;

    @Column(name = "ranking")
    private int rankings = 0;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
}
