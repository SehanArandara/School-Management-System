package org.schoolmanagement.studentmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.schoolmanagement.studentmanagement.DTO.SubjectReq;
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
    private SubjectRepo subjectRepo;

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
        return subjectRepo.findAll();
    }

    public Optional<Subject> getSingleSubject(String subjectId) {
        return subjectRepo.findById(subjectId);
    }

}
