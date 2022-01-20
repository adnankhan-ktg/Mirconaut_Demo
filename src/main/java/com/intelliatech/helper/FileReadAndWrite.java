package com.intelliatech.helper;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.util.Base64;

@Singleton
public class FileReadAndWrite {

    @Inject
    private FileUploadHelper fileUploadHelper;


//    public static void main(String[] args) throws Exception{
//
//        byte[] inFileBytes = Files.readAllBytes(Paths.get("D:\\Micronaut_Demo\\Mirconaut_Demo\\src\\main\\resources\\images"+File.separator+"sample.pdf"));
//        byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);
//        String str = Base64.getMimeEncoder().encodeToString(inFileBytes);
//        System.out.println(str);
//        System.out.println(encoded);
//
//
//    }

    public static void main(String[] args) {
        String product = "BIL";
        String cycle = "2021-9-01";
        String [] splitCycle = cycle.split("-");
        Integer monthNumber = Integer.valueOf(splitCycle[1]);
        String monthString = new DateFormatSymbols().getMonths()[monthNumber - 1];
        String sheetName = product+"_"+monthString+"_"+splitCycle[0];
        System.out.println(sheetName);
    }






}
