package com.intelliatech.helper;

import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Singleton
public class FileUploadHelper {


    public final String UPLOAD_DIR = "E:\\intelliatech\\Mirconaut_Demo\\src\\main\\resources\\images";
    public boolean uploadFile(CompletedFileUpload file)
    {
        boolean f = false;

        try{

            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+ File.separator+file.getFilename()), StandardCopyOption.REPLACE_EXISTING);
            f = true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

}
