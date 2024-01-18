package org.schoolmanagement.studentmanagement.repo;

import org.schoolmanagement.studentmanagement.models.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepo extends MongoRepository<Subject,String> {
}
