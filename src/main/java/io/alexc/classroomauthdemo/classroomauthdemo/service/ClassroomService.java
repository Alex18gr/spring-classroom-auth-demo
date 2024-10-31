package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {

    List<ClassroomDto> findAllClassrooms();

    ClassroomDto saveClassroom(ClassroomDto classroomDto);

    ClassroomDto updateClassroom(Long id, ClassroomDto classroomDto);

    void deleteClassroom(ClassroomDto classroomDto);

    void deleteClassroomById(Long id);

    ClassroomDto getClassroom(Long id);

}
