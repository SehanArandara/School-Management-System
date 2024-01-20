package org.schoolmanagement.studentmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.schoolmanagement.studentmanagement.execptions.ResourceNotFoundException;
import org.schoolmanagement.studentmanagement.models.Exam;
import org.schoolmanagement.studentmanagement.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamCtrl {

    private final ExamService examService ;

    @PostMapping
    public ResponseEntity<Exam> addExam(@RequestBody Exam exam){
        Exam exam1 = examService.addExam(exam);
        return new ResponseEntity<>(exam1, HttpStatus.OK);
    }

    @GetMapping
    public List<Exam> getAllExams (){
        return examService.getAllExams();
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Object> getExamById (@PathVariable String examId){
        try{
            Exam exam = examService.getSingleSubject(examId);
            return new ResponseEntity<>(exam,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{examId}")
    public ResponseEntity<Object> updateExam (@PathVariable String examId,Exam exam){
        try{
            examService.updateExam(examId,exam);
            return new ResponseEntity<>(exam,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<Object> deleteExam (@PathVariable String examId){
        try{
            examService.deleteExam(examId);
            return new ResponseEntity<>("exam ID under"+examId+"is delete",HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
