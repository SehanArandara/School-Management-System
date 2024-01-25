package org.schoolmanagement.teachersmanagement.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.schoolmanagement.teachersmanagement.Repo.TeacherRepo;
import org.schoolmanagement.teachersmanagement.execptions.ResourceNotFoundException;
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
        List<Teacher> teachers = teacherRepo.findAll();
        return teachers.stream().map(this::mapToTeacher).toList();
    }

    public Teacher addTeacher(Teacher teacher){
        Teacher teacher1 = Teacher.builder()
                .address(teacher.getAddress())
                .id(teacher.getId())
                .email(teacher.getEmail())
                .contactNumber(teacher.getContactNumber())
                .lastName(teacher.getLastName())
                .firstName(teacher.getFirstName())
                .build();

        log.info("new teacher is added"+teacher1.getFirstName());
        return teacherRepo.save(teacher1);
    }

    public Teacher getSingleTeacher(Long id){
        Optional<Teacher> teachers = teacherRepo.findById(id);
        return teachers.map(this::mapToTeacher)
                .orElseThrow(()-> new ResourceNotFoundException("teacher is not found"));
    }

    public void updateTeacher(Long id,Teacher teacher){
        Teacher exisitingTeacher = teacherRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(("teachr is not found")));

        exisitingTeacher.setEmail(teacher.getEmail());
        exisitingTeacher.setAddress(teacher.getAddress());
        exisitingTeacher.setId(teacher.getId());
        exisitingTeacher.setContactNumber(teacher.getContactNumber());
        exisitingTeacher.setLastName(teacher.getLastName());
        exisitingTeacher.setFirstName(teacher.getFirstName());

        teacherRepo.save(exisitingTeacher);
        log.info("teacher is updated"+exisitingTeacher.getFirstName());
    }

    public void deleteTeacher(Long id){
        Teacher existingTeacher = teacherRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("teacher is not found"));

        teacherRepo.delete(existingTeacher);
        log.info("teacher is deleted"+existingTeacher.getFirstName());
    }
    private Teacher mapToTeacher(Teacher teacher){
        log.info("teacher"+teacher.getFirstName()+" "+teacher.getLastName()+" is fetched");
        return Teacher.builder()
                .id(teacher.getId())
                .address(teacher.getAddress())
                .email(teacher.getEmail())
                .contactNumber(teacher.getContactNumber())
                .lastName(teacher.getLastName())
                .firstName(teacher.getFirstName())
                .address(teacher.getAddress())
                .build();
    }


}
