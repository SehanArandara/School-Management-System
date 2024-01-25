package org.schoolmanagement.teachersmanagement.Repo;

import org.schoolmanagement.teachersmanagement.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher,Long> {
}
