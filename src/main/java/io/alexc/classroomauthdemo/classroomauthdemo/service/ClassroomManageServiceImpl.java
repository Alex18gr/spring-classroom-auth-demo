package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import io.alexc.classroomauthdemo.classroomauthdemo.error.ClassroomNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.error.StudentNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.ClassroomRepository;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ClassroomManageServiceImpl implements ClassroomManageService {

    private final ClassroomService classroomService;

    private final StudentService studentService;

    private final ClassroomRepository classroomRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public ClassroomManageServiceImpl(ClassroomService classroomService, StudentService studentService, ClassroomRepository classroomRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.classroomService = classroomService;
        this.studentService = studentService;
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Collection<StudentDto> getClassroomStudents(Integer classroomId) {
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        return classroom.getStudents().stream()
                .map(this::convertStudentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto saveClassroomStudent(Integer classroomId, StudentDto studentDto) {
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        Student student = this.modelMapper.map(studentDto, Student.class);
        student.setClassroom(classroom);
        return this.modelMapper.map(this.studentRepository.save(student), StudentDto.class);
    }

    @Override
    public StudentDto getClassroomStudent(Integer classroomId, Integer studentId) {
        return this.modelMapper.map(this.studentRepository.findByClassroom_IdAndId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)), StudentDto.class);
    }

    @Override
    public StudentDto updateClassroomStudent(Integer classroomId, Integer studentId, StudentDto studentDto) {
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        Student student = this.modelMapper.map(studentDto, Student.class);
        student.setClassroom(classroom);
        return this.modelMapper.map(this.studentRepository.findById(studentId)
                .map(s -> {
                    s.setClassroom(classroom);
                    s.setBirthDate(student.getBirthDate());
                    s.setFirstName(student.getFirstName());
                    s.setLastName(student.getLastName());
                    s.setGrade(student.getGrade());
                    return this.studentRepository.save(s);
                })
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)), StudentDto.class);
    }

    @Override
    public void deleteClassroomStudent(Integer classroomId, Integer studentId) {
        this.studentRepository.delete(this.studentRepository.findByClassroom_IdAndId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
    }

    private Classroom convertClassroomToEntity(ClassroomDto classroomDto) {
        return modelMapper.map(classroomDto, Classroom.class);
    }

    private StudentDto convertStudentToDto(Student student) {
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }
}
