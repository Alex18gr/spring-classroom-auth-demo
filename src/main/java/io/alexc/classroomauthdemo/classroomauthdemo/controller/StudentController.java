package io.alexc.classroomauthdemo.classroomauthdemo.controller;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import io.alexc.classroomauthdemo.classroomauthdemo.error.StudentNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("students")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"}, maxAge = 3600)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<StudentDto> getStudents() {
        return this.studentService.getAllStudents();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public StudentDto getStudent(@PathVariable Integer id) {
        return this.studentService.getStudent(id);
    }

    @RequestMapping(value = "{id}/classroom", method = RequestMethod.GET)
    public ClassroomDto getStudentClassroom(@PathVariable Integer id) {
        return this.studentService.getStudentClassroom(id);
    }
}
