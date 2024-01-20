package org.schoolmanagement.studentmanagement.repo;

import org.schoolmanagement.studentmanagement.models.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepo extends MongoRepository<Exam,String> {
}
