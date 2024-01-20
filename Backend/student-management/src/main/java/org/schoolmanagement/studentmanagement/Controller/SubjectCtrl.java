package org.schoolmanagement.studentmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.schoolmanagement.studentmanagement.DTO.SubjectReq;
import org.schoolmanagement.studentmanagement.execptions.ResourceNotFoundException;
import org.schoolmanagement.studentmanagement.models.Subject;
import org.schoolmanagement.studentmanagement.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectCtrl {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> addSubject(@RequestBody SubjectReq subjectReq){
        Subject subject =subjectService.addSubject(subjectReq);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @GetMapping
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @GetMapping("{subjectId}")
    public ResponseEntity<Object> getSubjectById (@PathVariable String subjectId){
       try{
           Subject subject = subjectService.getSingleSubject(subjectId);
           return new ResponseEntity<>(subject,HttpStatus.OK);
       }catch (ResourceNotFoundException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<Object> updateSubject(@PathVariable String subjectId,@RequestBody Subject subject){
        try{
            subjectService.updateSubject(subjectId,subject);
            return new ResponseEntity<>(subject,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Object> deleteSubject(@PathVariable String subjectId){
        try{
            subjectService.deleteSubject(subjectId);
            return new ResponseEntity<>("Subject under Id :"+subjectId+"is deleted",HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
