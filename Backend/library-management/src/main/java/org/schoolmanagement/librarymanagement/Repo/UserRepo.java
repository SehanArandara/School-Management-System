package org.schoolmanagement.librarymanagement.Repo;

import org.schoolmanagement.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value = "SELECT * from user WHERE firstName= :firstName",nativeQuery = true)
    List<User> findByFirstName(@Param("firstName") String firstName);
}
