package org.schoolmanagement.studentmanagement.repo;

import org.schoolmanagement.studentmanagement.models.Mark;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MarkRepo extends MongoRepository<Mark,String> {
    List<Mark> findBySubjectId(String subjectId);
}
