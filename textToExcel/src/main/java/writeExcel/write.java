package writeExcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.tools.ant.taskdefs.Local;

import textRead.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.RowId;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class write {
    private static double sumAll = 0;
    private static String dateAll = "";
    public static void write(){
        String filePath = "C:/Borsa/Sibirya/Rapor/";
        String folderPath = "C:\\Borsa\\Sibirya\\Rapor";
        String fileName = "sibirya4Kar.xlsx";
        double sum = 0;


        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateToday = today.format(formatter);
        dateAll = dateToday;

        int arrListLen = read.readText(read.getPath(), dateToday).size();

        String[] forSum = new String[arrListLen];
        for(int b = 0;b<arrListLen;b++) {
            forSum[b] = read.readText(read.getPath(), dateToday).get(b);
            sum += Double.parseDouble(forSum[b]);
        }

        try (FileInputStream fileInputStream = new FileInputStream(filePath+fileName)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);

            if(workbook.getNumberOfSheets() == 2) {
                if(workbook.getSheetAt(0).getSheetName().equals("Kar_Data") && workbook.getSheetAt(1).getSheetName().equals("Kar_Kayıt")){
                    System.out.println("Sheets exist");
                }
            } else if (workbook.getNumberOfSheets() == 0){
                workbook.createSheet("Kar_Data");
                workbook.createSheet("Kar_Kayıt");
            } else {
                workbook.createSheet("Kar_Kayıt");
            }

            Sheet page = workbook.getSheet("Kar_Data");
            Row infoRow = page.createRow(0);
            Cell infoCell = infoRow.createCell(1);
            infoCell.setCellValue("Kar");

            Cell dateinfoCell = infoRow.createCell(3);
            dateinfoCell.setCellValue("Tarih");

            Cell suminfoCell = infoRow.createCell(5);
            suminfoCell.setCellValue("Toplam Kar");

            boolean sheetExist = false;

            int rowNum = 0;


            if(arrListLen == 0) {
                Row nullRow = page.createRow(1);
                Cell nullDateCell = nullRow.createCell(3);
                Cell nullSumCell = nullRow.createCell(5);
                nullDateCell.setCellValue(dateToday);
                nullSumCell.setCellValue(sum);
            }

            int k = 0;
            String data = "";

            String[] dataArr = new String[arrListLen];
            for(int a = 0; a<arrListLen; a++) {
                dataArr[a] = read.readText(read.getPath(), dateToday).get(a);
                System.out.println(dataArr[a]);
            }

            for(int i = 1; i < arrListLen+1; i++) {
                Row row = page.createRow(i);
                Cell cell = row.createCell(1);
                data = dataArr[k];
                k++;
                cell.setCellValue(data);
                if(i == 1){
                    Cell dateCell = row.createCell(3);
                    dateCell.setCellValue(dateToday);
                    Cell sumCell = row.createCell(5);
                    sumCell.setCellValue(sum);
                    sumAll = sum;
                }
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName)) {
                workbook.write(fileOutputStream);
                System.out.println("Dosya yazdırıldı.");
            }
            workbook.close();
        } catch (IOException e) {
            e.getMessage();
        }

        System.out.println(arrListLen);
    }

    public static double getSumAll() {
        return sumAll;
    }

    public static String getDateAll() {
        return dateAll;
    }
}
