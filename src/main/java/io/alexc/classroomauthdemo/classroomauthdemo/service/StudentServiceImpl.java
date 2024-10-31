package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import io.alexc.classroomauthdemo.classroomauthdemo.error.StudentNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.mapper.ClassroomMapper;
import io.alexc.classroomauthdemo.classroomauthdemo.mapper.StudentMapper;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.ClassroomRepository;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClassroomMapper classroomMapper;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        return studentMapper.studentToStudentDto(studentRepository.save(studentMapper.studentDtoToStudent(studentDto)));
    }

    @Override
    public void deleteStudent(StudentDto studentDto) {
        Student student = this.studentRepository.findById(studentDto.getId()).orElseThrow(() -> new StudentNotFoundException(studentDto.getId()));
        this.studentRepository.delete(student);
    }

    @Override
    public void deleteStudent(Long id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public StudentDto getStudent(Long id) {
        return studentMapper.studentToStudentDto(this.studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id)));
    }

    @Override
    public StudentDto findStudentByIdAndClassroomId(Long classroomId, Long studentId) {
        return studentMapper.studentToStudentDto(
                this.studentRepository.findByClassroom_IdAndId(classroomId, studentId)
                        .orElseThrow(() -> new StudentNotFoundException(studentId))
        );
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentMapper.studentsToStudentDtos(this.studentRepository.findAll());
    }

    @Override
    public ClassroomDto getStudentClassroom(Long id) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return classroomMapper.classroomToClassroomDto(student.getClassroom());
    }


}
