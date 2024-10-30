package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;

import java.util.Collection;

public interface ClassroomManageService {

    Collection<StudentDto> getClassroomStudents(Integer classroomId);

    StudentDto saveClassroomStudent(Integer classroomId, StudentDto studentDto);

    StudentDto getClassroomStudent(Integer classroomId, Integer studentId);

    StudentDto updateClassroomStudent(Integer classroomId, Integer studentId, StudentDto studentDto);

    void deleteClassroomStudent(Integer classroomId, Integer studentId);
}
