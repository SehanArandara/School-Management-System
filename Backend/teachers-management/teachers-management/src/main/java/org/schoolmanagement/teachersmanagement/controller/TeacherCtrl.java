package org.schoolmanagement.teachersmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.schoolmanagement.teachersmanagement.models.Teacher;
import org.schoolmanagement.teachersmanagement.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherCtrl {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeacher(){
        List<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getSingleTeacher(@PathVariable Long id){
//        return teacherService.getSingleTeacher(id)
//                .map(teacher -> new ResponseEntity<>(teacher,HttpStatus.OK))
//                .orElseGet(()-> new ResponseEntity<>("Teacher not found ",HttpStatus.NOT_FOUND));
//    }
}
