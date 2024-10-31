package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;

import java.util.Collection;

public interface ClassroomManageService {

    Collection<StudentDto> getClassroomStudents(Long classroomId);

    StudentDto saveClassroomStudent(Long classroomId, StudentDto studentDto);

    StudentDto getClassroomStudent(Long classroomId, Long studentId);

    StudentDto updateClassroomStudent(Long classroomId, Long studentId, StudentDto studentDto);

    void deleteClassroomStudent(Long classroomId, Long studentId);
}
