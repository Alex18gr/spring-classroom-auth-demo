package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDto saveStudent(StudentDto student);

    void deleteStudent(StudentDto student);

    void deleteStudent(int studentId);

    StudentDto getStudent(int id);

    StudentDto findStudentByIdAndClassroomId(int classroomId, int id);

    List<StudentDto> getAllStudents();

    ClassroomDto getStudentClassroom(Integer id);

}
