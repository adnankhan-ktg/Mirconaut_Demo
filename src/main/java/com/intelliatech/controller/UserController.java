package com.intelliatech.controller;

import com.intelliatech.bean.User;
import com.intelliatech.service.UserService;
import com.intelliatech.serviceimpl.UserServiceImpl;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Inject
    private UserService userService;



    @Post("/create")
    public HttpResponse<User> createUser(@Body User user)
    {
        log.info("Inside UserController in addUser()");
        User userOne = this.userService.addUser(user);
        log.info("Leaving UserController in addUser()");
        return HttpResponse.status(HttpStatus.OK).body(userOne);

    }

    @Get("/get/all")
    public HttpResponse<List<User>> getUsers()
    {
        log.info("Inside UserController in getUsers()");
        List<User> listOfUser = this.userService.getUsers();
        log.info("Leaving UserController in getUsers()");
        return HttpResponse.status(HttpStatus.OK).body(listOfUser);
    }

    @Get("/get/{userId}")
    public HttpResponse<User> getUser(@PathVariable("userId") int userId)
    {
        log.info("Inside UserController in getUser()");
        User user = this.userService.getUser(userId);
        log.info("Leaving UserController in getUser()");
        return HttpResponse.status(HttpStatus.OK).body(user);
    }
    @Delete("/delete/{userId}")
    public HttpResponse<?> deleteUser(int userId)
    {
        log.info("Inside UserController in deleteUser()");
        this.userService.deleteUser(userId);
        log.info("Leaving UserController in deleteUser()");
        return HttpResponse.ok();
    }

    @Put("/update")
    public HttpResponse<User> updateUser(@Body User user)
    {
        log.info("Inside UserController in updateUser()");
        User userOne = this.userService.updateUser(user);
        log.info("Leaving UserController in updateUser()");
        return HttpResponse.status(HttpStatus.OK).body(userOne);
    }





}
