package com.intelliatech.serviceimpl;

import com.intelliatech.bean.User;
import com.intelliatech.helper.ExcelOperation;
import com.intelliatech.queryExtractor.UserExtractor;
import com.intelliatech.repository.UserRepository;
import com.intelliatech.service.UserService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.runtime.http.PageableRequestArgumentBinder;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.poi.hssf.record.pivottable.PageItemRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    @Inject
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private ExcelOperation excelOperation;

    @Override
    public User addUser(User user) {
        log.info("Inside UserServiceImpl in addUser()");
        System.out.println(user);
        User userOne = this.userRepository.save(user);
        log.info("Leaving UserServiceImpl in addUser()");
        return userOne;
    }

    @Override
    public Page<User> getUsers(int offset,int page) {
        log.info("Inside UserServiceImpl in getUsers()");
        Pageable pageable = Pageable.from(page,offset);
//        Page<User> listOfUser = this.userRepository.findAll(pageable);
        Page<User> listOfUser = this.userRepository.findAllUsersWithPagination(pageable);
        log.info("Leaving UserServiceImpl in getUsers()");
        return listOfUser;

    }

    @Override
    public User getUser(int userId) {
        log.info("Inside UserServiceImpl in getUser");
        User user = this.userRepository.findById(userId);
        user.setSerialNumber(1);
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
        return userOne;
    }

    @Override
    public List<User> dumpExcel(CompletedFileUpload file) throws Exception {
        log.info("Inside ServiceImpl in dumpExcel()");
        boolean status = excelOperation.checkContentType(file);
        List<User> list = new ArrayList<>();

        if(status) {
            list = this.excelOperation.convertExcelToListOfProduct(file.getInputStream());
             List<User> listOfStoredUser = this.userRepository.saveAll(list);
                log.info("Leaving UserServiceImpl in dumpExcel()");
                return list;

        } else {
            log.info("Leaving UserServiceImpl in dumpExcel()");
            return list;
        }
    }


    @Override
    public void getExtractor(String value) {
//        List<UserExtractor> e = this.userRepository.getUserExtractor(value);
//        System.out.println(e);
//        System.out.println(e);

    }


    @Override
    public void createStatusReport(CompletedFileUpload file) throws Exception {
        log.info("Inside ServiceImpl in createStatusReport()");
        this.excelOperation.createStatusReport(file.getInputStream());
        log.info("Leaving ServiceImpl in createStatusReport()");
    }
}