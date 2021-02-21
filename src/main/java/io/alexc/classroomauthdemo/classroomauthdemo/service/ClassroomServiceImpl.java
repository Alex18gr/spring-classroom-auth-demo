package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import io.alexc.classroomauthdemo.classroomauthdemo.error.ClassroomNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.ClassroomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final ModelMapper modelMapper;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository, ModelMapper modelMapper) {
        this.classroomRepository = classroomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClassroomDto> findAllClassrooms() {
        return this.classroomRepository.findAll().stream()
                .map(this::convertClassroomToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassroomDto saveClassroom(ClassroomDto classroomDto) {
        return this.modelMapper
                .map(this.classroomRepository.save(this.modelMapper
                        .map(classroomDto, Classroom.class)), ClassroomDto.class);
    }

    @Override
    public ClassroomDto updateClassroom(Integer id, ClassroomDto classroomDto) {
        return this.classroomRepository.findById(id)
                .map(c -> {
                    c.setName(classroomDto.getName());
                    c.setStudents(classroomDto.getStudents().stream().map(this::convertStudentToEntity).collect(Collectors.toList()));
                    c.setId(id);
                    return this.convertClassroomToDto(this.classroomRepository.save(c));
                })
                .orElseThrow(() -> new ClassroomNotFoundException(id));
    }

    @Override
    public void deleteClassroom(ClassroomDto classroomDto) {
        Classroom classroomToDelete = this.classroomRepository.findById(classroomDto.getId()).orElseThrow(() -> new ClassroomNotFoundException(classroomDto.getId()));
        this.classroomRepository.delete(classroomToDelete);
    }

    @Override
    public void deleteClassroomById(int id) {
        if (this.classroomRepository.existsById(id)) {
            this.classroomRepository.deleteById(id);
        } else {
            throw new ClassroomNotFoundException();
        }
    }

    @Override
    public ClassroomDto getClassroom(Integer id) {
        return this.modelMapper.map(this.classroomRepository.findById(id)
                .orElseThrow(() -> new ClassroomNotFoundException(id)), ClassroomDto.class);
    }

    private ClassroomDto convertClassroomToDto(Classroom classroom) {
        ClassroomDto classroomDto = modelMapper.map(classroom, ClassroomDto.class);
        return classroomDto;
    }


    private Student convertStudentToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }
}
