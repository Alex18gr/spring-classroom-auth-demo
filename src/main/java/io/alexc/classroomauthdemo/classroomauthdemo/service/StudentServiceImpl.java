package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import io.alexc.classroomauthdemo.classroomauthdemo.error.StudentNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = this.convertStudentToEntity(studentDto);
        return this.convertStudentToDto(this.studentRepository.save(student));
    }

    @Override
    public void deleteStudent(StudentDto studentDto) {
        Student student = this.studentRepository.findById(studentDto.getId()).orElseThrow(() -> new StudentNotFoundException(studentDto.getId()));
        this.studentRepository.delete(student);
    }

    @Override
    public void deleteStudent(int id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public StudentDto getStudent(int id) {
        return this.modelMapper.map(this.studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id)), StudentDto.class);
    }

    @Override
    public StudentDto findStudentByIdAndClassroomId(int classroomId, int studentId) {
        Student student = this.studentRepository.findByClassroom_IdAndId(classroomId, studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        return this.convertStudentToDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return this.studentRepository.findAll().stream().map(this::convertStudentToDto).collect(Collectors.toList());
    }

    @Override
    public ClassroomDto getStudentClassroom(Integer id) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return this.modelMapper.map(student.getClassroom(), ClassroomDto.class);
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
