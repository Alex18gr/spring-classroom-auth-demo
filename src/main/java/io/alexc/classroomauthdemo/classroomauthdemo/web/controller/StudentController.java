package io.alexc.classroomauthdemo.classroomauthdemo.web.controller;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<StudentDto> getStudents() {
        return this.studentService.getAllStudents();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public StudentDto getStudent(@PathVariable Long id) {
        return this.studentService.getStudent(id);
    }

    @RequestMapping(value = "{id}/classroom", method = RequestMethod.GET)
    public ClassroomDto getStudentClassroom(@PathVariable Long id) {
        return this.studentService.getStudentClassroom(id);
    }
}
