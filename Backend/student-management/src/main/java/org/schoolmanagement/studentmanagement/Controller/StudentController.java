package org.schoolmanagement.studentmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.schoolmanagement.studentmanagement.DTO.StudentRequest;
import org.schoolmanagement.studentmanagement.DTO.StudentResponse;
import org.schoolmanagement.studentmanagement.execptions.ResourceNotFoundException;
import org.schoolmanagement.studentmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStudent(@RequestBody StudentRequest studentRequest){
        studentService.addNewStudent(studentRequest);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents(){
       return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Object> getStudentById(@PathVariable String studentId){
        try{
            StudentResponse studentResponse = studentService.getSingleStudent(studentId);
            return new ResponseEntity<>(studentResponse,HttpStatus.OK);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{stdId}")
    public ResponseEntity<Object> updateStudent(@PathVariable String stdId,@RequestBody StudentRequest studentRequest){
        try{
            studentService.updateStudent(stdId,studentRequest);
            return new ResponseEntity<>(studentRequest,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{stdId}")
    public ResponseEntity<String> deleteStudent(@PathVariable String stdId){
        try{
            studentService.deleteStudent(stdId);
            return new ResponseEntity<>("Student is deleted under ID :"+stdId,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
