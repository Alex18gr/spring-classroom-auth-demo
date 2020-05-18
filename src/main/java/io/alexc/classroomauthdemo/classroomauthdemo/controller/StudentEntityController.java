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
public class StudentEntityController implements StudentController {

    private final StudentService studentService;

    private final ModelMapper modelMapper;

    public StudentEntityController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<StudentDto> getStudents() {
        return this.studentService.findAll().stream().map(this::convertStudentToDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public StudentDto getStudent(@PathVariable Integer id) {
        return this.modelMapper.map(this.studentService.findStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException(id)), StudentDto.class);
    }

    @RequestMapping(value = "{id}/classroom", method = RequestMethod.GET)
    public ClassroomDto getStudentsClassrooms(@PathVariable Integer id) {
        Student student = this.studentService.findStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return this.modelMapper.map(student.getClassroom(), ClassroomDto.class);
    }

    private ClassroomDto convertClassroomToDto(Classroom classroom) {
        ClassroomDto classroomDto = modelMapper.map(classroom, ClassroomDto.class);
        return classroomDto;
    }

    private Classroom convertClassroomToEntity(ClassroomDto classroomDto) {
        return modelMapper.map(classroomDto, Classroom.class);
    }

    private StudentDto convertStudentToDto(Student student) {
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    private Student convertStudentToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

}
