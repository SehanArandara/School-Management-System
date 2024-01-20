package org.schoolmanagement.studentmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.schoolmanagement.studentmanagement.DTO.SubjectReq;
import org.schoolmanagement.studentmanagement.execptions.ResourceNotFoundException;
import org.schoolmanagement.studentmanagement.models.Subject;
import org.schoolmanagement.studentmanagement.repo.SubjectRepo;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {
    private final SubjectRepo subjectRepo;

    public Subject addSubject(SubjectReq subjectReq){
        Subject subject = Subject.builder()
                .id(subjectReq.getId())
                .category(subjectReq.getCategory())
                .name(subjectReq.getName())
                .build();

        log.info("new subject "+subject.getName()+"is added");
        return subjectRepo.save(subject);
    }

    public List<Subject> getAllSubjects(){
        List<Subject> subjectList = subjectRepo.findAll();
        return subjectList.stream().map(this::mapToSubject).toList();
    }

    public Subject getSingleSubject(String subjectId) {
        Optional<Subject> subjectList = subjectRepo.findById(subjectId);
        return subjectList.map(this::mapToSubject)
                .orElseThrow(()-> new ResourceNotFoundException("subject is not found"));
    }

    public Subject mapToSubject(Subject subject){
        log.info("Subject"+subject.getName() +" is fetched");
        return Subject.builder()
                .id(subject.getId())
                .name(subject.getName())
                .category(subject.getCategory())
                .build();
    }

    public void updateSubject(String subjectId, Subject subject) {
        Subject existingSubject = subjectRepo.findById(subjectId)
                .orElseThrow(()-> new ResourceNotFoundException("subject is not found"));

        existingSubject.setId(subject.getId());
        existingSubject.setName(subject.getName());
        existingSubject.setCategory(subject.getCategory());

        subjectRepo.save(existingSubject);
        log.info("subject name :"+existingSubject.getName()+" is updated");
    }

    public void deleteSubject(String subjectId) {
        Subject existingSubject = subjectRepo.findById(subjectId)
                .orElseThrow(()-> new ResourceNotFoundException("subject is not found"));

        subjectRepo.delete(existingSubject);
        log.info("subject with id "+ subjectId + " is deleted");
    }
}
