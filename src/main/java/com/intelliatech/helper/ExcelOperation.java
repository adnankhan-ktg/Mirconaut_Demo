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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        String tempName = "khan";
        excelOperation(tempName);
        System.out.println("failed = " + failed);
        System.out.println("pass   = " + pass);
        System.out.println("total  = "+ total );
        System.out.println(tempName + " = "+lastName);
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

}
