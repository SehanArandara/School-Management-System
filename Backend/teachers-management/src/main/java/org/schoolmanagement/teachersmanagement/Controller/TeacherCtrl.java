package org.schoolmanagement.teachersmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.schoolmanagement.teachersmanagement.Service.TeacherService;
import org.schoolmanagement.teachersmanagement.execptions.ResourceNotFoundException;
import org.schoolmanagement.teachersmanagement.models.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherCtrl {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        Teacher teacher1 = teacherService.addTeacher(teacher);
        return new ResponseEntity<>(teacher1, HttpStatus.OK);
    }

    @GetMapping
    public List<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Object> getSingleTeacher(@PathVariable Long id){
        try{
            Teacher teacher = teacherService.getSingleTeacher(id);
            return new ResponseEntity<>(teacher,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Object> updateTeacher(@PathVariable Long id,@RequestBody Teacher teacher){
        try{
            teacherService.updateTeacher(id,teacher);
            return new ResponseEntity<>(teacher,HttpStatus.OK);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable Long id){
        try{
            teacherService.deleteTeacher(id);
            return  new ResponseEntity<>("teacher deleted"+id,HttpStatus.OK);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
