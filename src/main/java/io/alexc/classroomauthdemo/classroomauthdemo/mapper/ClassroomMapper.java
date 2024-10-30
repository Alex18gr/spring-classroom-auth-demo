package io.alexc.classroomauthdemo.classroomauthdemo.mapper;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    ClassroomDto classroomToClassroomDto(Classroom classroom);

    Classroom classroomDtoToClassroom(ClassroomDto classroomDto);

    List<ClassroomDto> classroomsToClassroomDtos(List<Classroom> classrooms);

    List<Classroom> classroomDtosToClassrooms(List<ClassroomDto> classroomDtos);
}
