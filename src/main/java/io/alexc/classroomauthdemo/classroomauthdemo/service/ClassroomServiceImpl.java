package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import io.alexc.classroomauthdemo.classroomauthdemo.error.ClassroomNotFoundException;
import io.alexc.classroomauthdemo.classroomauthdemo.mapper.ClassroomMapper;
import io.alexc.classroomauthdemo.classroomauthdemo.mapper.StudentMapper;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final StudentMapper studentMapper;

    @Override
    public List<ClassroomDto> findAllClassrooms() {
        return classroomMapper.classroomsToClassroomDtos(this.classroomRepository.findAll());
    }

    @Override
    public ClassroomDto saveClassroom(ClassroomDto classroomDto) {
        return classroomMapper.classroomToClassroomDto(
                classroomRepository.save(
                        classroomMapper.classroomDtoToClassroom(classroomDto)
                )
        );
    }

    @Override
    public ClassroomDto updateClassroom(Integer id, ClassroomDto classroomDto) {
        return this.classroomRepository.findById(id)
                .map(c -> {
                    c.setName(classroomDto.getName());
                    c.setStudents(studentMapper.studentDtosToStudents(classroomDto.getStudents()));
                    c.setId(id);
                    return classroomMapper.classroomToClassroomDto(this.classroomRepository.save(c));
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
        return classroomMapper.classroomToClassroomDto(
                this.classroomRepository.findById(id)
                        .orElseThrow(() -> new ClassroomNotFoundException(id))
        );
    }

}
