package com.intelliatech.service;

import com.intelliatech.bean.User;
import io.micronaut.data.model.Page;
import io.micronaut.http.multipart.CompletedFileUpload;

import java.util.List;

public interface UserService {

    User addUser(User user);
    Page<User> getUsers(int offset, int page);
    User getUser(int userId);
    void deleteUser(int userId);
    User updateUser(User user);
    List<User> dumpExcel(CompletedFileUpload file) throws Exception;
    public void getExtractor(String value);
    public void createStatusReport(CompletedFileUpload file) throws Exception;
}
