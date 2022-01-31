package com.moon.senla.educational_website.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
