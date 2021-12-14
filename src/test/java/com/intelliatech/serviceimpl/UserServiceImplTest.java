package com.intelliatech.serviceimpl;

import com.intelliatech.bean.User;
import com.intelliatech.repository.UserRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void addUser() {

        User user = new User(1,"adnan","8964882358",19);

        Mockito.when(userRepository.save(user)).thenReturn(user);


         User returnedUser = this.userService.addUser(user);

        assertEquals(1,returnedUser.getId());
    }

    @Test
    void getUsers() {

        List<User> listOfUser = new ArrayList<>();
        listOfUser.add(new User(1,"dheeraj","89648823258",19));
        listOfUser.add(new User(2,"aditya","89648823251",25));
        listOfUser.add(new User(3,"ramesh","89648823252",28));
        listOfUser.add(new User(4,"praveen","89648823253",30));
        listOfUser.add(new User(5,"sumit","89648823254",32));


        Mockito.when(userRepository.findAll()).thenReturn(listOfUser);

        List<User> list = this.userService.getUsers();

        assertEquals(5,list.size());

    }

    @Test
    void getUser() {



        Map<Integer,User> map = new HashMap<>();

        map.put(1,new User(1,"dheeraj","89648823258",19));
        map.put(2,new User(2,"aditya","89648823251",25));
        map.put(3,new User(3,"ramesh","89648823252",28));
        map.put(4,new User(4,"praveen","89648823253",30));
        map.put(5,new User(5,"sumit","89648823254",32));

        int userId = 1;

        when(userRepository.findById(userId)).thenReturn(map.get(userId));

        User res = this.userService.getUser(userId);

        assertEquals(map.get(1).getUserName(),res.getUserName());

    }

    @Test
    void deleteUser() {

        doNothing().when(userRepository).deleteById(1);

        this.userService.deleteUser(1);

    }

    @Test
    void updateUser() {

        User user = new User(1,"dheeraj","8964882358",19);
        when(userRepository.update(user)).thenReturn(user);

        User objectForReturn = this.userService.updateUser(user);

        assertEquals("dheeraj",objectForReturn.getUserName());
    }
}