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
    public ResponseEntity<List<Subject>> getAllSubjects(){
        List<Subject> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects,HttpStatus.OK);
    }

//    @GetMapping("{subjectId}")
//    public ResponseEntity<Object> getSubjectById (@PathVariable String subjectId){
//        return subjectService.getSingleSubject(subjectId)
//                .map(subject -> new ResponseEntity<>(subject,HttpStatus.OK))
//                .orElseGet(()-> new ResponseEntity<>("subject not found",HttpStatus.NOT_FOUND));
//    }
}
