package io.alexc.classroomauthdemo.classroomauthdemo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/*
 The entity class the represents the Classroom entity using hibernate annotations
 */
@Entity
@Getter @Setter
@Table(name = "classrooms")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="_id")
public class Classroom {

    @Id
    @Column(name = "classroom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "classroom_name")
    private String name;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Student> students;

}
