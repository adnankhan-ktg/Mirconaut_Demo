package com.intelliatech.repository;

import com.intelliatech.bean.User;
import com.intelliatech.queryExtractor.UserExtractor;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "SELECT * FROM user",
    countQuery = "SELECT count(*) FROM user",
    nativeQuery = true)
    Page<User> findAllUsersWithPagination(Pageable pageable);


// @Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1 ORDER BY ?#{#pageable}",
//       countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
//       nativeQuery = true)
//   Page<User> findByLastname(String lastname, Pageable pageable);

    User findById(int id);
}
