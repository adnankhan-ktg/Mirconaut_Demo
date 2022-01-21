package com.intelliatech.controller;

import com.intelliatech.bean.User;
import com.intelliatech.service.PdfFileService;
import com.intelliatech.service.UserService;
import com.intelliatech.serviceimpl.UserServiceImpl;
import io.micronaut.data.model.Page;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Inject
    private UserService userService;

    @Inject
    private PdfFileService pdfFileService;



    @Post("/create")
    public HttpResponse<User> createUser(@Body User user)
    {
        log.info("Inside UserController in addUser()");
        User userOne = this.userService.addUser(user);
        log.info("Leaving UserController in addUser()");
        return HttpResponse.status(HttpStatus.OK).body(userOne);

    }

    @Get("/get/all/{offset}/{page}")
    public HttpResponse<?> getUsers(int offset, int page)
    {
        log.info("Inside UserController in getUsers()");
        Page<User> listOfUser = this.userService.getUsers(offset, page);
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


    @Post(value = "/uploadExcel",consumes = {MediaType.MULTIPART_FORM_DATA})
    public HttpResponse<List> dumpExcel(@Part CompletedFileUpload file) throws IOException, SQLException,Exception
    {
        log.info("Inside UserController in dumpExcel()");

         List<User> list = this.userService.dumpExcel(file);

        log.info("Leaving UserController in dumpExcel()");
        return HttpResponse.status(HttpStatus.OK).body(list);
    }

    @Post(value = "/getStatusReport",consumes = {MediaType.MULTIPART_FORM_DATA})
    public HttpResponse<?> getStatusReport(@Part CompletedFileUpload file) throws IOException, SQLException,Exception
    {
        log.info("Inside UserController in getStatusReport()");

         this.userService.createStatusReport(file);

        log.info("Leaving UserController in getStatusReport()");
        return HttpResponse.status(HttpStatus.OK);
    }
    @Get("/getExtractor/{value}")
 public void getExtractor(@PathVariable("value") String value)

 {
     this.userService.getExtractor(value);
 }

    @Post(value = "/uploadPdfFile", consumes = { MediaType.MULTIPART_FORM_DATA })
    public String uploadPdfFile(@Part CompletedFileUpload file)
    {
        log.info("Inside UserController in uploadPdfFile()");

        String url = this.pdfFileService.uploadFile(file);
        log.info("Leaving UserController in uploadPdfFile()");
        return url;
    }
}

