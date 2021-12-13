package com.intelliatech.helper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

@Component
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
//                                System.out.print(cell.getNumericCellValue() + "\t");
                            status(cell.getNumericCellValue());
                            break;
                        case STRING:
//                                System.out.print(cell.getStringCellValue() + "\t");
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
}
