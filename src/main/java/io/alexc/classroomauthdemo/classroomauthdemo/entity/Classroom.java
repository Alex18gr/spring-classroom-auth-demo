package io.alexc.classroomauthdemo.classroomauthdemo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/*
 The entity class the represents the Classroom entity using hibernate annotations
 */
@Entity
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }
}
