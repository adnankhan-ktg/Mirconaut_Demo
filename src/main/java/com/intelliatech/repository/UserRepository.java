package com.intelliatech.repository;

import com.intelliatech.bean.User;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findById(int id);
}
