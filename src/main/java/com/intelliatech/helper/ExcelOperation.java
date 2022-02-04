package com.intelliatech.helper;

import com.intelliatech.bean.User;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Singleton
public class ExcelOperation {


    private static int failed = 0;
    private static int pass = 0;
    private static int total = 0;
    private static int lastName = 0;

    public static void excelOperation(String name)
    {

        try {
            FileInputStream file = new FileInputStream(new File("E:\\intelliatech projects\\New Microsoft Excel Worksheet.xlsx"));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    // Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            status(cell.getNumericCellValue());
                            break;
                        case STRING:
                            byLastName(name,cell.getStringCellValue());
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void status(double value)
    {
        ++total;
        if(value == 0)
        {
            ++failed;
        }else{
            ++pass;
        }
    }


    public static void byLastName(String name,String excelName)
    {
        if(name.equals(excelName))
        {
            ++lastName;

        }
    }



    //check that file is of excel type or not
    public boolean checkContentType(CompletedFileUpload file)
    {
        String contentType = file.getContentType().get().toString();

        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        {
            return true;
        }
        else{
        return false;
        }
    }



    public List<User> convertExcelToListOfProduct(InputStream is)
    {
        List<User> list = new ArrayList<>();

        try{

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while(iterator.hasNext())
            {
                Row row = iterator.next();
                if(rowNumber == 0)
                {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;

                User user = new User();
                while(cells.hasNext())
                {
                    Cell cell = cells.next();

                    switch (cid)
                    {
                        case 0:
                              user.setUserName(cell.getStringCellValue());
                              break;
                        case 1:
                              user.setUserAge((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                System.out.println(user);
                list.add(user);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return list;
    }


    public static void getExcelFile()
    {

        // Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("user Details");

        // This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{ "ID", "NAME", "LASTNAME" });
        data.put("2", new Object[]{ 1, "Pankaj", "Kumar" });
        data.put("3", new Object[]{ 2, "Prakashni", "Yadav" });
        data.put("4", new Object[]{ 3, "Ayan", "Mondal" });
        data.put("5", new Object[]{ 4, "Virat", "kohli" });

        // Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // this line creates a cell in the next column of that row
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String)obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try {

            FileOutputStream out = new FileOutputStream(new File("E:\\excel\\abc.xlsx"));
            workbook.write(out);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }



    public static void getDatabaseExcel()
    {

        try {


            List<User> list = new ArrayList<>();


            list.add(new User(1, "adnan", "69584123", 20));
            list.add(new User(2, "dheeraj", "8964882357", 25));
            list.add(new User(3, "manoj", "8964882355", 26));
            list.add(new User(4, "manoj", "8964882355", 26));
            list.add(new User(5, "manoj", "8964882355", 26));
            list.add(new User(6, "manoj", "8964882355", 26));
            list.add(new User(7, "manoj", "8964882355", 26));
            list.add(new User(8, "manoj", "8964882355", 26));

            String[] columns = {"mobile_number", "user_age", "user_name"};

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("User Data");

            Row headerRow = sheet.createRow(0);


            //.........................
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.getCTFont().addNewB();
            cellStyle.setFont(font);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setLocked(true);
            //.........................


            String cellValue = "1234567890";
            XSSFCellStyle alterableStyle = (XSSFCellStyle)workbook.createCellStyle(); //  Get the style object of the current cell
            alterableStyle.setLocked(true); //  Set this cell to be locked
            XSSFCellStyle nolockedStyle = (XSSFCellStyle)workbook.createCellStyle(); //  Get the style object of the current cell
            nolockedStyle.setLocked(false); //  Set this cell to be unlocked
//----------------------------------------------------------------------


            String[] datas = new String[]{"maintain", "restore", "adjust"};
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                    .createExplicitListConstraint(datas);
            CellRangeAddressList addressList = null;
            XSSFDataValidation validation = null;
            ///////////////////////////////////////////
            //////////////////////////////////////////

            int firstColumn = 2;
            int lastColumn = 2;
            int firstRow = 1;
            int lastRow = list.size();
//            for(int k = 1; k <=8; ++k)
//            {
            addressList = new CellRangeAddressList(firstRow, lastRow, firstColumn, lastColumn);
            validation = (XSSFDataValidation) dvHelper.createValidation(
                    dvConstraint, addressList);
            //07 default setSuppressDropDownArrow (true);
            //validation.setSuppressDropDownArrow (true);
            //validation.setShowErrorBox (true);
            sheet.addValidationData(validation);
//            }

//-----------------------------------------------------------------

            for (int j = 0; j < columns.length; ++j) {
                Cell cell = headerRow.createCell(j);
                cell.setCellValue(columns[j]);
                cell.setCellStyle(cellStyle);
                cell.setCellStyle(nolockedStyle);
            }

            int rowNumber = 1;

            for (User u : list) {
                Row row = sheet.createRow(rowNumber++);
                row.createCell(0).setCellValue(u.getMobileNumber());
                row.createCell(1).setCellValue(u.getUserAge());
                row.createCell(2).setCellValue(u.getUserName());
            }

//            ---------------------------------------------------------------




//            ---------------------------------------------------------------
            FileOutputStream fileOut = new FileOutputStream("E:\\intelliatech\\abc.xlsx");
            workbook.write(fileOut);
            fileOut.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void getDatabaseExcelForStatus()
    {


        //--------------------------------------
        List<User> list = new ArrayList<>();
        list.add(new User(1, "adnan khan", "8964882358", 20,"no"));


        //-------------------------------------

        try {

            String[] columns = {"UserName", "MobileNumber", "UserAge","Status"};

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("sheet1");
            Row headerRow = sheet.createRow(0);
            sheet.protectSheet("123");

            //.........................
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.getCTFont().addNewB();
            cellStyle.setFont(font);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setLocked(false);
            //.........................


            for (int j = 0; j < columns.length; ++j) {
                Cell cell = headerRow.createCell(j);
                cell.setCellValue(columns[j]);
                cell.setCellStyle(cellStyle);
                cell.setCellStyle(cellStyle);
            }

            int rowNumber = 1;


            for (User u : list) {
                Row row = sheet.createRow(rowNumber++);
                row.createCell(0).setCellValue(u.getUserName());
                row.createCell(1).setCellValue(u.getMobileNumber());
                row.createCell(2).setCellValue(u.getUserAge());
                row.createCell(3).setCellValue(u.getStatus());
            }
//            FileOutputStream fileOut = new FileOutputStream("E:\\intelliatech\\abc.xlsx");
//            workbook.write(fileOut);
//            fileOut.close();

//            --------------------------------------------

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            try {
                workbook.write(b);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
            byte[] bytes = b.toByteArray();
//            Base64.encodeBase64String(bytes);
              String s = Base64.getMimeEncoder().encodeToString(bytes);
            System.out.println(s);




//            --------------------------------------------
        }catch(Exception e){
            e.printStackTrace();
        }
    }




    public int createStatusReport(InputStream is)
    {
        List<User> list = new ArrayList<>();

        int yesCount = 0;
        int noCount = 0;
        int totalCount = 0;

        try{

            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("sheet1");

            String name = workbook.getSheetAt(0).getSheetName();

            Row headerRow = sheet.getRow(0);

            if(!headerRow.getCell(3).getStringCellValue().equals("Status")) {
                return 0;
            }

            Iterator<Row> iterator = sheet.iterator();


            while(iterator.hasNext())
            {
                Row row = iterator.next();

                Cell cell = row.getCell(3);

                if(cell.getStringCellValue().equals("no"))
                {
                    noCount++;
                }
                else if(cell.getStringCellValue().equals("yes"))
                {
                    yesCount++;
                }
                      totalCount++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
             totalCount--;
        System.out.println("no  = "+noCount);
        System.out.println("yes = "+yesCount);
        System.out.println("total = "+totalCount);
        return 0;
    }

    public static void zipFileDownload()
    {
        try {
            FileOutputStream fos = new FileOutputStream("multifiles.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            String file1Name = "E:\\intelliatech\\abc1.xlsx";
            String file2Name = "E:\\intelliatech\\abc2.xlsx";

            //Creating the workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.getSheetAt(0);
            workbook.write(zos);
//
//            System.out.println("Writing '" + fileName + "' to zip file");
//
//            File file = new File(fileName);
//            FileInputStream fis = new FileInputStream(file);
//            ZipEntry zipEntry = new ZipEntry(fileName);
//            zos.putNextEntry(zipEntry);
//
//            byte[] bytes = new byte[1024];
//            int length;
//            while ((length = fis.read(bytes)) >= 0) {
//                zos.write(bytes, 0, length);
//            }

//            zos.closeEntry();
//            fis.close();
//
//            zos.close();
//            fos.close();

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            try {
                workbook.write(b);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
            byte[] bytes = b.toByteArray();
//            Base64.encodeBase64String(bytes);
            String s = Base64.getMimeEncoder().encodeToString(bytes);
            System.out.println(s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

        System.out.println("Writing '" + fileName + "' to zip file");

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }

    public static void main(String[] args) {

//        String tempName = "khan";
//        excelOperation(tempName);
//        System.out.println("failed = " + failed);
//        System.out.println("pass   = " + pass);
//        System.out.println("total  = "+ total );
//        System.out.println(tempName + " = "+lastName);
//        getExcelFile();
//        getDatabaseExcel();
//        getDatabaseExcelForStatus();
//        getDatabaseExcel();
//        zipFileDownload();
    }


    public void zip() throws Exception
    {
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("sheet1");
//
//        FileOutputStream fos = new FileOutputStream("multifiles.zip");
//        ZipOutputStream zos = new ZipOutputStream(fos);
//           workbook.write(fos);
//        ZipEntry zipEntry = new ZipEntry(fos);
        try
        {
            //Source files
            String fileName1 = "C:\\Users\\Anubhav\\Desktop\\file1.txt";
            String fileName2 = "C:\\Users\\Anubhav\\Desktop\\file2.txt";
            //Zipped file
            String zipFilename = "C:\\Users\\Anubhav\\Desktop\\Allfiles.zip";
            File zipFile = new File(zipFilename);
            FileOutputStream fos  = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zipFile(fileName1, zos);
            zipFile(fileName2, zos);
            zos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Method to zip file
    private static void zipFile(String fileName, ZipOutputStream zos) throws IOException
    {
        final int BUFFER = 1024;
        BufferedInputStream bis = null;
        try
        {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis, BUFFER);

            // ZipEntry --- Here file name can be created using the source file
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);
            byte data[] = new byte[BUFFER];
            int count;
            while((count = bis.read(data, 0, BUFFER)) != -1)
            {
                zos.write(data, 0, count);
            }
            // close entry every time
            zos.closeEntry();
        }
        finally
        {
            try
            {
                bis.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}


