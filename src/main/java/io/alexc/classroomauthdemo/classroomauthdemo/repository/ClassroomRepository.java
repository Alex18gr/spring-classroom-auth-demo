package io.alexc.classroomauthdemo.classroomauthdemo.repository;


import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
