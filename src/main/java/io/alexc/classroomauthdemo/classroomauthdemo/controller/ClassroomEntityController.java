package io.alexc.classroomauthdemo.classroomauthdemo.controller;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import io.alexc.classroomauthdemo.classroomauthdemo.error.ClassroomNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.error.StudentNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.service.ClassroomService;
import io.alexc.classroomauthdemo.classroomauthdemo.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("classrooms")
// For fixing the CORS issues due to different domain with the front-end
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"}, maxAge = 3600)
public class ClassroomEntityController implements ClassroomController {

    private final ModelMapper modelMapper;

    private final ClassroomService classroomService;

    private final StudentService studentService;

    public ClassroomEntityController(ClassroomService classroomService, StudentService studentService, ModelMapper modelMapper) {
        this.classroomService = classroomService;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ClassroomDto> getClassrooms() {

        return classroomService.findAllClassrooms().stream()
                .map(this::convertClassroomToDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ClassroomDto postClassroom(
            @RequestBody ClassroomDto classroomDto
    ) {
        return this.modelMapper
                .map(this.classroomService.saveClassroom(this.modelMapper
                        .map(classroomDto, Classroom.class)), ClassroomDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ClassroomDto getClassroom(@PathVariable Integer id) {
        return this.modelMapper.map(this.classroomService.findById(id)
                .orElseThrow(() -> new ClassroomNotFoundException(id)), ClassroomDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ClassroomDto putClassroom(
            @RequestBody ClassroomDto classroomDto,
            @PathVariable Integer id) {
        return classroomService.findById(id)
                .map(c -> {
                    c.setName(classroomDto.getName());
                    c.setStudents(classroomDto.getStudents().stream().map(this::convertStudentToEntity).collect(Collectors.toList()));
                    return modelMapper.map(this.classroomService.saveClassroom(c), ClassroomDto.class);
                })
                .orElseThrow(() -> new ClassroomNotFoundException(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteClassroom(
            @PathVariable Integer id
    ) {
        this.classroomService.deleteClassroomById(id);
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.GET)
    public Collection<StudentDto> getClassroomStudents(@PathVariable Integer classroomId) {
        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        return classroom.getStudents().stream()
                .map(this::convertStudentToDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.POST)
    public StudentDto postClassroomStudent(
            @PathVariable Integer classroomId,
            @RequestBody StudentDto studentDto
    ) {
        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        Student student = this.modelMapper.map(studentDto, Student.class);
        student.setClassroom(classroom);
        return this.modelMapper.map(this.studentService.save(student), StudentDto.class);
    }
    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.GET)
    public StudentDto getClassroomStudentById(
            @PathVariable Integer classroomId,
            @PathVariable Integer studentId
    ) {
        return this.modelMapper.map(this.studentService.findStudentByIdAndClassroomId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)), StudentDto.class);
    }
    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.PUT)
    public StudentDto putClassroomStudent(
            @PathVariable Integer classroomId,
            @PathVariable Integer studentId,
            @RequestBody StudentDto studentDto
    ) {
        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        Student student = this.modelMapper.map(studentDto, Student.class);
        student.setClassroom(classroom);
        return this.modelMapper.map(this.studentService.findStudentById(studentId)
                .map(s -> {
                    s.setClassroom(classroom);
                    s.setBirthDate(student.getBirthDate());
                    s.setFirstName(student.getFirstName());
                    s.setLastName(student.getLastName());
                    s.setGrade(student.getGrade());
                    return this.studentService.save(s);
                })
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)), StudentDto.class);
    }
    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.DELETE)
    public void deleteClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId) {
        this.studentService.deleteStudent(this.studentService.findStudentByIdAndClassroomId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
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
