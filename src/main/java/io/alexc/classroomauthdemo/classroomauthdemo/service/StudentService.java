package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Student save(Student student);

    public void deleteStudent(Student student);

    public void deleteStudentById(int id);

    public Optional<Student> findStudentById(int id);

    public Optional<Student> findStudentByIdAndClassroomId(int classroomId, int id);

    public List<Student> findAll();

}
