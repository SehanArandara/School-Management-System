package org.schoolmanagement.studentmanagement.repo;

import org.schoolmanagement.studentmanagement.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, String> {
}
