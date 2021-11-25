package com.intelliatech.serviceimpl;

import com.intelliatech.bean.User;
import com.intelliatech.repository.UserRepository;
import com.intelliatech.service.UserService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
     log.info("Inside UserServiceImpl in addUser()");
     User userOne = this.userRepository.save(user);
     log.info("Leaving UserServiceImpl in addUser()");
     return userOne;
    }

    @Override
    public List<User> getUsers() {
        log.info("Inside UserServiceImpl in getUsers()");
        List<User> listOfUser = this.userRepository.findAll();
        log.info("Leaving UserServiceImpl in getUsers()");
        return listOfUser;

    }

    @Override
    public User getUser(int userId) {
        log.info("Inside UserServiceImpl in getUser");
        User user = this.userRepository.findByUserId(userId);
        log.info("Leaving UserServiceImpl in getUser");
        return user;

    }

    @Override
    public void deleteUser(int userId) {
        log.info("Inside UserServiceImpl in deleteUser()");
        this.userRepository.deleteById(userId);
        log.info("Leaving UserServiceImpl in deleteUser()");

    }

    @Override
    public User updateUser(User user) {
        log.info("Inside UserServiceImpl in updateUser()");
        User userOne = this.userRepository.update(user);
        log.info("Leaving UserServiceImpl in updateUser()");
        return user;
    }
}
