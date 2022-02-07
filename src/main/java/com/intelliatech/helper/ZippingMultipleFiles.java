package com.intelliatech.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZippingMultipleFiles
{

    public static void main(String[] args) throws IOException{

//        String src [] = {"\\src\\resource\\excelFile\\workbook1.xlsx","\\src\\resource\\excelFile\\workbook2.xlsx"};
  String src [] = {"D:\\PROJECTS\\MICRONAUT_POC\\Mirconaut_Demo\\src\\main\\resources\\excelFile\\workbook1.xlsx",
  "D:\\PROJECTS\\MICRONAUT_POC\\Mirconaut_Demo\\src\\main\\resources\\excelFile\\workbook2.xlsx"};
        List<MemoryFile> memoryFile = new ArrayList<>();


        byte[] array1 = Files.readAllBytes(Paths.get(src[0]));
        byte[] array2 = Files.readAllBytes(Paths.get(src[1]));
        memoryFile.add(new MemoryFile("workbook1.xlsx",array1));
        memoryFile.add(new MemoryFile("workbook2.xlsx",array2));
         byte [] bytes = createZipByteArray(memoryFile);
        String s = Base64.getMimeEncoder().encodeToString(bytes);
        System.out.println(s);
    }
    public static class MemoryFile {
        public String fileName;
        public byte[] contents;

        public MemoryFile(String fileName, byte[] contents) {
            this.fileName = fileName;
            this.contents = contents;
        }
    }

    public static byte[] createZipByteArray(List<MemoryFile> memoryFiles) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        try {
            for (MemoryFile memoryFile : memoryFiles) {
                ZipEntry zipEntry = new ZipEntry(memoryFile.fileName);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(memoryFile.contents);
                zipOutputStream.closeEntry();
            }
        } finally {
            zipOutputStream.close();
        }

        //Delete file code
        File file = new File("D:\\PROJECTS\\MICRONAUT_POC\\Mirconaut_Demo\\src\\main\\resources\\excelFile\\workbook1.xlsx");
        if(file.delete())
        {
            System.out.println("file is deleted");
        }else{
            System.out.println("file is not deleted");
        }
        return byteArrayOutputStream.toByteArray();
    }

}
