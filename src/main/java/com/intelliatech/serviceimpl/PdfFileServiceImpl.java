package com.intelliatech.serviceimpl;

import com.intelliatech.helper.FileUploadHelper;
import com.intelliatech.service.PdfFileService;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class PdfFileServiceImpl implements PdfFileService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    private FileUploadHelper fileUploadHelper;

    @Override
    public String uploadFile(CompletedFileUpload file) {

        log.info("Inside PdfFileServiceImpl in uploadFile()");
        String fileName = file.getFilename();
        Long fileSize  = file.getSize();
        String contentType = file.getContentType().toString();
        System.out.println("original name = "+file.getFilename());
        System.out.println("size = "+file.getSize());
//        ------------------------------------------------------
        boolean status = fileUploadHelper.uploadFile(file);
        System.out.println(fileUploadHelper.UPLOAD_DIR+"\\"+file.getFilename());
        String fileUrl = fileUploadHelper.UPLOAD_DIR+"\\"+file.getFilename();
//        ------------------------------------------------------
        log.info("Leaving PdfFileServiceImpl in uploadFile()");
        return fileUrl;


    }
}
