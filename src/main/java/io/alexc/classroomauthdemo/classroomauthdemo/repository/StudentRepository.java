package io.alexc.classroomauthdemo.classroomauthdemo.repository;


import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    Optional<Student> findByClassroom_IdAndId(Long classroom_id, Long id);
}
