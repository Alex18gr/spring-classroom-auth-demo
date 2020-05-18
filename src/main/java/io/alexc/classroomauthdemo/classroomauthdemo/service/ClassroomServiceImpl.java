package io.alexc.classroomauthdemo.classroomauthdemo.service;

import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<Classroom> findAllClassrooms() {
        return this.classroomRepository.findAll();
    }

    @Override
    public Classroom saveClassroom(Classroom classroom) {
        return this.classroomRepository.save(classroom);
    }

    @Override
    public void deleteClassroom(Classroom classroom) {
        this.classroomRepository.delete(classroom);
    }

    @Override
    public void deleteClassroomById(int id) {
        this.classroomRepository.deleteById(id);
    }

    @Override
    public Optional<Classroom> findById(Integer id) {
        return this.classroomRepository.findById(id);
    }
}
