package io.alexc.classroomauthdemo.classroomauthdemo.web.controller;

import io.alexc.classroomauthdemo.classroomauthdemo.dto.ClassroomDto;
import io.alexc.classroomauthdemo.classroomauthdemo.dto.StudentDto;
import io.alexc.classroomauthdemo.classroomauthdemo.service.ClassroomManageService;
import io.alexc.classroomauthdemo.classroomauthdemo.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    private final ClassroomManageService classroomManageService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ClassroomDto> getClassrooms() {
        return classroomService.findAllClassrooms();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ClassroomDto createClassroom(@RequestBody ClassroomDto classroomDto) {
        return this.classroomService.saveClassroom(classroomDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ClassroomDto getClassroom(@PathVariable Long id) {
        return this.classroomService.getClassroom(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ClassroomDto updateClassroom(@RequestBody ClassroomDto classroomDto, @PathVariable Long id) {
        return this.classroomService.updateClassroom(id, classroomDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteClassroom(@PathVariable Long id) {
        this.classroomService.deleteClassroomById(id);
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.GET)
    public Collection<StudentDto> getClassroomStudents(@PathVariable Long classroomId) {
        return this.classroomManageService.getClassroomStudents(classroomId);
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.POST)
    public StudentDto saveClassroomStudent(@PathVariable Long classroomId, @RequestBody StudentDto studentDto) {
        return this.classroomManageService.saveClassroomStudent(classroomId, studentDto);
    }

    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.GET)
    public StudentDto getClassroomStudent(@PathVariable Long classroomId, @PathVariable Long studentId) {
        return this.classroomManageService.getClassroomStudent(classroomId, studentId);
    }

    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.PUT)
    public StudentDto updateClassroomStudent(@PathVariable Long classroomId, @PathVariable Long studentId, @RequestBody StudentDto studentDto) {
        return this.classroomManageService.updateClassroomStudent(classroomId, studentId, studentDto);
    }

    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.DELETE)
    public void deleteClassroomStudent(@PathVariable Long classroomId, @PathVariable Long studentId) {
        this.classroomManageService.deleteClassroomStudent(classroomId, studentId);
    }



}
