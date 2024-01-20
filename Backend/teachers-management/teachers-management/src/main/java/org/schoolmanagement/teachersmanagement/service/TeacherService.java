package org.schoolmanagement.teachersmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.schoolmanagement.teachersmanagement.Repo.TeacherRepo;
import org.schoolmanagement.teachersmanagement.models.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final TeacherRepo teacherRepo;

    public List<Teacher> getAllTeachers(){
        return teacherRepo.findAll();
    }

    public Optional<Teacher> getSingleTeacher(Long id) {
        return teacherRepo.findById(id);
    }
}
