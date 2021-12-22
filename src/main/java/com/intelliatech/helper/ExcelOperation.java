package com.intelliatech.helper;

import com.intelliatech.bean.User;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

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
    public static void main(String[] args) {

//        String tempName = "khan";
//        excelOperation(tempName);
//        System.out.println("failed = " + failed);
//        System.out.println("pass   = " + pass);
//        System.out.println("total  = "+ total );
//        System.out.println(tempName + " = "+lastName);
//        getExcelFile();
        getDatabaseExcel();
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
            System.out.println("gfgcontribute.xlsx written successfully on disk.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void getDatabaseExcel()
    {

        try {


            List<User> list = new ArrayList<>();
            list.add(new User(1, "adnan", null, 20));
            list.add(new User(2, "dheeraj", "8964882357", 25));
            list.add(new User(3, "manoj", "8964882355", 26));

            String[] columns = {"mobile_number", "user_age", "user_name"};

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("User Data");
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; ++i) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNumber = 1;

            for (User u : list) {
                Row row = sheet.createRow(rowNumber++);
                row.createCell(0).setCellValue(u.getMobileNumber());
                row.createCell(1).setCellValue(u.getUserAge());
                row.createCell(2).setCellValue(u.getUserName());
}

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

        FileOutputStream fileOut = new FileOutputStream("E:\\excel\\mno.csv");
        workbook.write(fileOut);
        fileOut.close();
        }catch (Exception e){
            e.printStackTrace();
        }






}}
