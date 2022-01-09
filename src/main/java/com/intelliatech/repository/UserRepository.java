package com.intelliatech.repository;

import com.intelliatech.bean.User;
import com.intelliatech.queryExtractor.UserExtractor;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    @Query("SELECT new com.intelliatech.queryExtractor.UserExtractor"
            + "(u.userName AS userName,"
            + "u.mobileNumber as number)"
            + "FROM User u where u.status = coalesce(nullif(:s,''),u.status)")
    public List<UserExtractor> getUserExtractor(String s);

    User findById(int id);
}
