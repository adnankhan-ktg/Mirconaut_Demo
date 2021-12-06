package com.intelliatech.controller;

import com.intelliatech.bean.User;
import com.intelliatech.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doNothing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    void createUser() {

        User user = new User(1,"adnan",19);
        Mockito.when(userService.addUser(user)).thenReturn(user);


        HttpResponse<User> res = this.userController.createUser(user);

        assertEquals(200,res.code());
    }

    @Test
    void getUsers() {

        List<User> listOfUser = new ArrayList<>();
        listOfUser.add(new User(1,"dheeraj",19));
        listOfUser.add(new User(2,"aditya",25));
        listOfUser.add(new User(3,"ramesh",28));
        listOfUser.add(new User(4,"praveen",30));
        listOfUser.add(new User(5,"sumit",32));


        Mockito.when(userService.getUsers()).thenReturn(listOfUser);


        HttpResponse<List<User>> res = this.userController.getUsers();

        assertEquals(200,res.code());
    }

    @Test
    void getUser() {

        Map<Integer,User> map = new HashMap<>();

        map.put(1,new User(1,"dheeraj",19));
        map.put(2,new User(2,"aditya",25));
        map.put(3,new User(3,"ramesh",28));
        map.put(4,new User(4,"praveen",30));
        map.put(5,new User(5,"sumit",32));

        int userId = 1;

        when(userService.getUser(userId)).thenReturn(map.get(userId));

        HttpResponse<User> response = this.userController.getUser(userId);

        assertEquals(HttpStatus.OK,response.status());


    }

    @Test
    void deleteUser() {

        doNothing().when(userService).deleteUser(1);

        HttpResponse<?> res = this.userController.deleteUser(1);

        assertEquals(HttpStatus.OK,res.status());
    }

    @Test
    void updateUser() {

        User user = new User(1,"adnan",19);

        Mockito.when(userService.updateUser(user)).thenReturn(user);


        HttpResponse<User> res = this.userController.updateUser(user);

        assertEquals(200,res.code());
    }
}