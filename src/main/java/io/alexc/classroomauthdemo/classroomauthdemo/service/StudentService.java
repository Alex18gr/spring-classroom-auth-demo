package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDto saveStudent(StudentDto student);

    void deleteStudent(StudentDto student);

    void deleteStudent(Long studentId);

    StudentDto getStudent(Long id);

    StudentDto findStudentByIdAndClassroomId(Long classroomId, Long id);

    List<StudentDto> getAllStudents();

    ClassroomDto getStudentClassroom(Long id);

}
