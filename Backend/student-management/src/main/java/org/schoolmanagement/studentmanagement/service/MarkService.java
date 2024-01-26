package org.schoolmanagement.studentmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.schoolmanagement.studentmanagement.execptions.ResourceNotFoundException;
import org.schoolmanagement.studentmanagement.models.Mark;
import org.schoolmanagement.studentmanagement.models.Subject;
import org.schoolmanagement.studentmanagement.repo.MarkRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarkService {

    private final MarkRepo markRepo;

    public Mark addMarks(Mark marks) {
        Mark newMarks = Mark.builder()
                .id(marks.getId())
                .studentId(marks.getStudentId())
                .examId(marks.getExamId())
                .subjectId(marks.getSubjectId())
                .marksObtained(marks.getMarksObtained())
                .totalMarks(marks.getTotalMarks())
                .grade(marks.getGrade())
                .dateOfExam(marks.getDateOfExam())
                .comments(marks.getComments())
                .teacherId(marks.getTeacherId())
                .classId(marks.getClassId())
                .schoolId(marks.getSchoolId())
                .build();

        log.info("New marks added for studentId: {}", marks.getStudentId());
        return markRepo.save(newMarks);
    }

    public List<Mark> getAllMarks() {
        List<Mark> marksList = markRepo.findAll();
        return marksList.stream().map(this::mapToMarks).toList();
    }

    private Mark mapToMarks(Mark marks) {
        return Mark.builder()
                .id(marks.getId())
                .studentId(marks.getStudentId())
                .examId(marks.getExamId())
                .subjectId(marks.getSubjectId())
                .marksObtained(marks.getMarksObtained())
                .totalMarks(marks.getTotalMarks())
                .grade(marks.getGrade())
                .dateOfExam(marks.getDateOfExam())
                .comments(marks.getComments())
                .teacherId(marks.getTeacherId())
                .classId(marks.getClassId())
                .schoolId(marks.getSchoolId())
                .build();
    }

    public Mark getSingleMarks(String marksId) {
        Optional<Mark> marksOptional = markRepo.findById(marksId);
        return marksOptional.map(this::mapToMarks)
                .orElseThrow(() -> new ResourceNotFoundException("Marks not found with id: " + marksId));
    }

    public void updateMarks(String marksId, Mark marks) {
        Mark existingMarks = markRepo.findById(marksId)
                .orElseThrow(() -> new ResourceNotFoundException("Marks not found with id: " + marksId));

        existingMarks.setStudentId(marks.getStudentId());
        existingMarks.setExamId(marks.getExamId());
        existingMarks.setSubjectId(marks.getSubjectId());
        existingMarks.setMarksObtained(marks.getMarksObtained());
        existingMarks.setTotalMarks(marks.getTotalMarks());
        existingMarks.setGrade(marks.getGrade());
        existingMarks.setDateOfExam(marks.getDateOfExam());
        existingMarks.setComments(marks.getComments());
        existingMarks.setTeacherId(marks.getTeacherId());
        existingMarks.setClassId(marks.getClassId());
        existingMarks.setSchoolId(marks.getSchoolId());

        markRepo.save(existingMarks);
        log.info("Marks updated for marksId: {}", marksId);
    }

    public void deleteMarks(String marksId) {
        Mark existingMarks = markRepo.findById(marksId)
                .orElseThrow(() -> new ResourceNotFoundException("Marks not found with id: " + marksId));

        markRepo.delete(existingMarks);
        log.info("Marks deleted for marksId: {}", marksId);
    }

    public List<Mark> getAllMarksBySubjectId(String subjectId){
        List<Mark> markList = markRepo.findBySubjectId(subjectId);
        return markList.stream().map(this::mapToMarks).toList();
    }
}
