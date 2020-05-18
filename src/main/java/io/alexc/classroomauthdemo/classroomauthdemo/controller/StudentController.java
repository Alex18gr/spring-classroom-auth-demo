package io.alexc.classroomauthdemo.classroomauthdemo.controller;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StudentController {

    public List<StudentDto> getStudents();

    public StudentDto getStudent(@PathVariable Integer id);

    public ClassroomDto getStudentsClassrooms(@PathVariable Integer id);

}
