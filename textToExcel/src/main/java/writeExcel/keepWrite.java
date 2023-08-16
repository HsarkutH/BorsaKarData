package writeExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class keepWrite {
    private static String filePath = "C:/Borsa/Sibirya/Rapor/sibirya4Kar.xlsx";
    public static void keepWrite() {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            boolean sheetExist = false;
            System.out.println(workbook.getSheetAt(0).getSheetName());
            System.out.println(workbook.getNumberOfSheets());
            if(workbook.getNumberOfSheets() == 1) {
                workbook.createSheet("Kar_Kayıt");
            }
            for(int p = 0; p<workbook.getNumberOfSheets();p++) {
                if (workbook.getSheetAt(p).getSheetName().equals("Kar_Kayıt")) {
                    sheetExist = true;
                }

            }
            Sheet sheet = workbook.getSheet("Kar_Kayıt");

            if(sheet.getRow(0) == null) {
                Row infoRowAll = sheet.createRow(0);
                Cell dateInfoCellAll = infoRowAll.createCell(0);
                dateInfoCellAll.setCellValue("Tarih");
                Cell sumInfoCellAll = infoRowAll.createCell(1);
                sumInfoCellAll.setCellValue("Toplam Kar");
            }

            int i = 0;
            //System.out.println(sheet.getLastRowNum());
            int lastRow = sheet.getLastRowNum();
            Row newRow = sheet.createRow(lastRow+1);

            Cell cellDate = newRow.createCell(0);
            cellDate.setCellValue(write.getDateAll());
            sheet.autoSizeColumn(0);
            Cell cellSum = newRow.createCell(1);
            cellSum.setCellValue(write.getSumAll());
            sheet.autoSizeColumn(1);

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            sheet.protectSheet("Kar_Kayıt");
            fileOutputStream.close();
            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
