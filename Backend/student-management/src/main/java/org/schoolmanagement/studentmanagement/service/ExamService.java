package org.schoolmanagement.studentmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.schoolmanagement.studentmanagement.execptions.ResourceNotFoundException;
import org.schoolmanagement.studentmanagement.models.Exam;
import org.schoolmanagement.studentmanagement.repo.ExamRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamService {

    private final ExamRepo examRepo;

    public Exam addExam(Exam exam) {
        Exam exam1 =  Exam.builder()
                .id(exam.getId())
                .examName(exam.getExamName())
                .date(exam.getDate())
                .stdId(exam.getStdId())
                .subjectId(exam.getSubjectId())
                .time(exam.getTime())
                .venue(exam.getVenue())
                .build();

        log.info("new exam is added"+exam1.getExamName());
        return examRepo.save(exam);
    }

    public List<Exam> getAllExams() {
        List<Exam> examList = examRepo.findAll();
        return examList.stream().map(this::mapToExam).toList();
    }

    private Exam mapToExam(Exam exam) {
        log.info("exam "+exam.getExamName()+" is feteched");
        return Exam.builder()
                .venue(exam.getVenue())
                .examName(exam.getExamName())
                .date(exam.getDate())
                .id(exam.getExamName())
                .stdId(exam.getStdId())
                .subjectId(exam.getSubjectId())
                .time(exam.getTime())
                .build();
    }

    public Exam getSingleSubject(String examId) {
        Optional<Exam> examList =  examRepo.findById(examId);
        return examList.map(this::mapToExam)
                .orElseThrow(()-> new ResourceNotFoundException("Exam is not found"));
    }

    public void updateExam(String examId, Exam exam) {
        Exam existingExam = examRepo.findById(examId)
                .orElseThrow(()-> new ResourceNotFoundException(("exam is not found")));

        existingExam.setId(exam.getId());
        existingExam.setExamName(exam.getExamName());
        existingExam.setDate(exam.getDate());
        existingExam.setTime(exam.getTime());
        existingExam.setVenue(exam.getVenue());
        existingExam.setStdId(exam.getStdId());
        existingExam.setSubjectId(exam.getSubjectId());

        examRepo.save(existingExam);
        log.info("exam name "+ existingExam.getExamName() + "is updated");
    }

    public void deleteExam(String examId) {
        Exam existingExam = examRepo.findById(examId)
                .orElseThrow(()-> new ResourceNotFoundException("exam is not fouund"));

        examRepo.delete(existingExam);
        log.info("exam under id "+examId +" is deleted");
    }
}
