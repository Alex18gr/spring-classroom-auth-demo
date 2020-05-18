package io.alexc.classroomauthdemo.classroomauthdemo.controller;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Classroom;
import io.alexc.classroomauthdemo.classroomauthdemo.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

public interface ClassroomController {

    public List<ClassroomDto> getClassrooms();

    public ClassroomDto postClassroom(@RequestBody ClassroomDto classroom);

    public ClassroomDto getClassroom(@PathVariable Integer id);

    public ClassroomDto putClassroom(@RequestBody ClassroomDto classroom, @PathVariable Integer id);

    public void deleteClassroom(@PathVariable Integer id);

    public Collection<StudentDto> getClassroomStudents(@PathVariable Integer classroomId);

    public StudentDto postClassroomStudent(@PathVariable Integer classroomId, @RequestBody StudentDto student);

    public StudentDto getClassroomStudentById(@PathVariable Integer classroomId, @PathVariable Integer studentId);

    public StudentDto putClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId, @RequestBody StudentDto student);

    public void deleteClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId);

}
