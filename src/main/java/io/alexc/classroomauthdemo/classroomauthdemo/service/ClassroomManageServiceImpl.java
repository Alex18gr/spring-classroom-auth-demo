package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import io.alexc.classroomauthdemo.classroomauthdemo.error.ClassroomNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.error.StudentNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.mapper.ClassroomMapper;
import io.alexc.classroomauthdemo.classroomauthdemo.mapper.StudentMapper;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.ClassroomRepository;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomManageServiceImpl implements ClassroomManageService {

    private final ClassroomService classroomService;
    private final StudentService studentService;
    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;
    private final ClassroomMapper classroomMapper;
    private final StudentMapper studentMapper;

    @Override
    public Collection<StudentDto> getClassroomStudents(Integer classroomId) {
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        return classroom.getStudents().stream()
                .map(studentMapper::studentToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto saveClassroomStudent(Integer classroomId, StudentDto studentDto) {
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        Student student = studentMapper.studentDtoToStudent(studentDto);
        student.setClassroom(classroom);
        return studentMapper.studentToStudentDto(this.studentRepository.save(student));
    }

    @Override
    public StudentDto getClassroomStudent(Integer classroomId, Integer studentId) {
        return studentMapper.studentToStudentDto(this.studentRepository.findByClassroom_IdAndId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
    }

    @Override
    public StudentDto updateClassroomStudent(Integer classroomId, Integer studentId, StudentDto studentDto) {
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        Student student = studentMapper.studentDtoToStudent(studentDto);
        student.setClassroom(classroom);
        return studentMapper.studentToStudentDto(this.studentRepository.findById(studentId)
                .map(s -> {
                    s.setClassroom(classroom);
                    s.setBirthDate(student.getBirthDate());
                    s.setFirstName(student.getFirstName());
                    s.setLastName(student.getLastName());
                    s.setGrade(student.getGrade());
                    return this.studentRepository.save(s);
                })
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
    }

    @Override
    public void deleteClassroomStudent(Integer classroomId, Integer studentId) {
        this.studentRepository.delete(this.studentRepository.findByClassroom_IdAndId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
    }

}
