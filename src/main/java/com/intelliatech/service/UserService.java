package com.intelliatech.service;

import com.intelliatech.bean.User;
import io.micronaut.http.multipart.CompletedFileUpload;

import java.util.List;

public interface UserService {

    User addUser(User user);
    List<User> getUsers();
    User getUser(int userId);
    void deleteUser(int userId);
    User updateUser(User user);
    List<User> dumpExcel(CompletedFileUpload file) throws Exception;
    public void getExtractor();
    public void createStatusReport(CompletedFileUpload file) throws Exception;
}
