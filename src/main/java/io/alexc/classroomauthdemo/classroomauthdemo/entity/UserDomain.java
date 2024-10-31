package io.alexc.classroomauthdemo.classroomauthdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class UserDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(nullable = false)
    private Boolean enabled;

    @OneToOne
    private Professor professor;

    @OneToMany(mappedBy = "userDomain")
    private List<Authority> authorities;


}
