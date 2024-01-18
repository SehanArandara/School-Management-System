package org.schoolmanagement.studentmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.schoolmanagement.studentmanagement.DTO.StudentRequest;
import org.schoolmanagement.studentmanagement.DTO.StudentResponse;
import org.schoolmanagement.studentmanagement.execptions.ResourceNotFoundException;
import org.schoolmanagement.studentmanagement.models.Student;
import org.schoolmanagement.studentmanagement.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepo studentRepo;

    public void addNewStudent(StudentRequest studentRequest) {

        Student student = Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .birthDay(studentRequest.getBirthDay())
                .address(studentRequest.getAddress())
                .grade(studentRequest.getGrade())
                .contactNumber(studentRequest.getContactNumber())
                        .build();

        studentRepo.save(student);
        log.info("Student is saved :"+student.getFirstName() +" " + student.getLastName());
    }

    public List<StudentResponse> getAllStudents() {
        List<Student> studentList = studentRepo.findAll();

        // change it from student to studentResponse
        return studentList.stream().map(this::mapStudentResponse).toList();

    }

    public StudentResponse getSingleStudent(String studentId){
        Optional<Student> studentList = studentRepo.findById(studentId);

        return studentList.map(this::mapStudentResponse)
                .orElseThrow(()-> new ResourceNotFoundException("student Not founded" + studentId));
    }

    public void updateStudent(String id,StudentRequest studentRequest){
        Student existingStudent = studentRepo.findById(id)
                .orElseThrow(()-> new ResolutionException("student is not found"+ id));

        existingStudent.setFirstName(studentRequest.getFirstName());
        existingStudent.setLastName(studentRequest.getLastName());
        existingStudent.setGrade(studentRequest.getGrade());
        existingStudent.setAddress(studentRequest.getAddress());
        existingStudent.setBirthDay(studentRequest.getAddress());
        existingStudent.setContactNumber(studentRequest.getContactNumber());

        studentRepo.save(existingStudent);
        log.info("student  name with "+ existingStudent.getFirstName() +" "+ existingStudent.getLastName() + "is updates");
    }

    public void deleteStudent(String stdId) {
        Student existingStudent = studentRepo.findById(stdId)
                .orElseThrow(()-> new ResolutionException("student is not found"));

        studentRepo.delete(existingStudent);
        log.info("student "+existingStudent.getFirstName() + " "+ existingStudent.getLastName()+"is deleted");
    }

    public StudentResponse mapStudentResponse(Student student){
        log.info("Student "+student.getFirstName()+" are fetched");
        return StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .birthDay(student.getBirthDay())
                .address(student.getAddress())
                .contactNumber(student.getContactNumber())
                .grade(student.getGrade())
                .build();
    }


}
