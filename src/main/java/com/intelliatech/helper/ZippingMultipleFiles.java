package com.intelliatech.helper;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZippingMultipleFiles
{
//    public static void main(String[] args)
//    {
//        try
//        {
//            //Source files
//            String fileName1 = "D:\\excel\\abc1.xlsx";
//            String fileName2 = "D:\\excel\\abc2.xlsx";
//            String fileName3 = "D:\\excel\\abc3.xlsx";
//
//            //Zipped file
//            String zipFilename = "D:\\excel\\m\\merge.zip";
//            File zipFile = new File(zipFilename);
//            FileOutputStream fos  = new FileOutputStream(zipFile);
//            ZipOutputStream zos = new ZipOutputStream(fos);
//
//                XSSFWorkbook workbook = new XSSFWorkbook();
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                workbook.write(bos);
//                byte[] barray = bos.toByteArray();
//                FileInputStream is = new ByteArrayInputStream(barray);
//
//            zipFile(fileName1, zos);
//            zipFile(fileName2, zos);
//            zos.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    // Method to zip file
//    private static void zipFile(InputStream fis, ZipOutputStream zos) throws IOException
//    {
//        final int BUFFER = 1024;
//        BufferedInputStream bis = null;
//        try
//        {
//            ResponseBuilder
////            File file = new File(fileName);
////            InputStream fis = new InputStream(file);
//            bis = new BufferedInputStream(fis, BUFFER);
//
//            // ZipEntry --- Here file name can be created using the source file
//            ZipEntry zipEntry = new ZipEntry(file.getName());
//            zos.putNextEntry(zipEntry);
//            byte data[] = new byte[BUFFER];
//            int count;
//            while((count = bis.read(data, 0, BUFFER)) != -1)
//            {
//                zos.write(data, 0, count);
//            }
//            // close entry every time
//            zos.closeEntry();
//        }
//        finally
//        {
//            try
//            {
//                bis.close();
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }

//    public static byte[] zipBytes(String filename, byte[] input) throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("sheet1");
//        workbook.write(baos);
//        ZipOutputStream zos = new ZipOutputStream(baos);
//        ZipEntry entry = new ZipEntry("abc");
//        entry.setSize(input.length);
//        zos.putNextEntry(entry);
//        zos.write(input);
//        zos.closeEntry();
//        zos.close();
//        return baos.toByteArray();
//    }
//
//    public static void main(String[] args) throws Exception {
//         byte [] arr = new byte[10];
//          arr = zipBytes("abc",arr);
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(arr.length);
//        baos.write(arr, 0, arr.length);
//
//        String s = Base64.getMimeEncoder().encodeToString(arr);
//
//        System.out.println(s);
//    }

//    public static void main(String[] args) {
//
//        String zipFile = "D:\\excel\\m\\min.zip";
//
//        String[] srcFiles = { "D:\\excel\\abc1.xlsx", "D:\\excel\\abc2.xlsx", "D:\\excel\\abc3.xlsx"};
//
//
//        try {
//
//            // create byte buffer
//            byte[] buffer = new byte[1024];
//
//            FileOutputStream fos = new FileOutputStream(zipFile);
//
//            ZipOutputStream zos = new ZipOutputStream(fos);
//
//            for (int i=0; i < srcFiles.length; i++) {
//
//                File srcFile = new File(srcFiles[i]);
//
//                FileInputStream fis = new FileInputStream(srcFile);
//
//                // begin writing a new ZIP entry, positions the stream to the start of the entry data
//                zos.putNextEntry(new ZipEntry(srcFile.getName()));
//
//                int length;
//
//                while ((length = fis.read(buffer)) > 0) {
//                    zos.write(buffer, 0, length);
//                }
//
//                zos.closeEntry();
//
//                // close the InputStream
//                fis.close();
//
//            }
//
//            // close the ZipOutputStream
//            zos.close();
//
//        }
//        catch (IOException ioe) {
//            System.out.println("Error creating zip file: " + ioe);
//        }
//
//
//    }
public static void main(String[] args) throws Exception{

    XSSFWorkbook workbook1 = new XSSFWorkbook();
    XSSFSheet sheet1 = workbook1.createSheet("sheet100");

    XSSFWorkbook workbook2 = new XSSFWorkbook();
    XSSFSheet sheet2 = workbook2.createSheet("sheet2");

    FileOutputStream out1 = new FileOutputStream(
            new File("D:\\intelliatech\\Mirconaut_Demo\\src\\main\\resources\\excelFile\\workbook1.xlsx"));
       workbook1.write(out1);
       out1.close();
    FileOutputStream out2 = new FileOutputStream(
            new File("D:\\intelliatech\\Mirconaut_Demo\\src\\main\\resources\\excelFile\\workbook2.xlsx"));
    workbook2.write(out2);
    out2.close();

    String zipFile = "D:\\excel\\m\\file.zip";

//    String[] srcFiles = { "D:\\excel\\abc1.xlsx", "D:\\excel\\abc2.xlsx", "D:\\excel\\abc3.xlsx"};
    String [] srcFiles = {"D:\\intelliatech\\Mirconaut_Demo\\src\\main\\resources\\excelFile\\workbook1.xlsx","D:\\intelliatech\\Mirconaut_Demo\\src\\main\\resources\\excelFile\\workbook2.xlsx"};

    try {

        // create byte buffer
        byte[] buffer = new byte[1024];

        FileOutputStream fos = new FileOutputStream(zipFile);

        ZipOutputStream zos = new ZipOutputStream(fos);

        for (int i=0; i < srcFiles.length; i++) {

            File srcFile = new File(srcFiles[i]);

            FileInputStream fis = new FileInputStream(srcFile);

            // begin writing a new ZIP entry, positions the stream to the start of the entry data
            zos.putNextEntry(new ZipEntry(srcFile.getName()));

            int length;

            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();

            // close the InputStream
            fis.close();

        }

        // close the ZipOutputStream
        zos.close();

    }
    catch (IOException ioe) {
        System.out.println("Error creating zip file: " + ioe);
    }
//    FileInputStream fileInputStream = new FileInputStream("D:\\excel\\m\\file.zip");

    File file = new File("D:\\excel\\m\\file.zip");
//    byte[] array = Files.readAllBytes(Paths.get("D:\\excel\\m\\file.zip"));
    byte[] array = Files.readAllBytes(Paths.get("D:\\intelliatech\\Mirconaut_Demo\\src\\main\\resources\\excelFile\\workbook1.xlsx"));
    String s = Base64.getMimeEncoder().encodeToString(array);
    System.out.println(s);



}

//    private static String zipB64(List<File> files) throws IOException {
//        byte[] buffer = new byte[1024];
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
//            for (File f : files) {
//                try (FileInputStream fis = new FileInputStream(f)) {
//                    zos.putNextEntry(new ZipEntry(f.getName()));
//                    int length;
//                    while ((length = fis.read(buffer)) > 0) {
//                        zos.write(buffer, 0, length);
//                    }
//                    zos.closeEntry();
//                }
//            }
//        }
//        byte[] bytes = baos.toByteArray();
//        return Base64.getEncoder().encodeToString(bytes);
//    }

//    public static void main(String[] args) {
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("sheet1");
//
//        String s = "I am in";
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try(ZipOutputStream zos = new ZipOutputStream(baos)) {
//
//  /* File is not on the disk, test.txt indicates
//     only the file name to be put into the zip */
//            ZipEntry entry = new ZipEntry("test.xlsx");
//
//            zos.putNextEntry(entry);
////            zos.write(s.getBytes());
//            workbook.write(zos);
//            zos.closeEntry();
//
//  /* use more Entries to add more files
//     and use closeEntry() to close each file entry */
//
//        } catch(IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//        byte[] bytes = baos.toByteArray();
////            Base64.encodeBase64String(bytes);
//        String ss = Base64.getMimeEncoder().encodeToString(bytes);
//        System.out.println(ss);
//    }
}