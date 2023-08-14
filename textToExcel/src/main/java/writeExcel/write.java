package writeExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.tools.ant.taskdefs.Local;

import textRead.read;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class write {
    public static void write(){
        String filePath = "C:/robotsibirya(4)/robot1/";
        String fileName = "Kar.xlsx";
        double sum = 0;

        File excelFile = new File(filePath + fileName);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateToday = today.format(formatter);

        int arrListLen = read.readText(read.getPath(), "07.08.2023").size();

        String[] forSum = new String[arrListLen];
        for(int b = 0;b<arrListLen;b++) {
            forSum[b] = read.readText(read.getPath(), "07.08.2023").get(b);
            sum += Double.parseDouble(forSum[b]);
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet page = workbook.createSheet("Kar_Data");

            Row infoRow = page.createRow(0);
            Cell infoCell = infoRow.createCell(1);
            infoCell.setCellValue("Kar");

            Cell dateinfoCell = infoRow.createCell(3);
            dateinfoCell.setCellValue("Tarih");

            Cell suminfoCell = infoRow.createCell(5);
            suminfoCell.setCellValue("Toplam Kar");

            int k = 0;
            String data = "";

            String[] dataArr = new String[arrListLen];
            for(int a = 0; a<arrListLen; a++) {
                dataArr[a] = read.readText(read.getPath(), "07.08.2023").get(a);
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
                }
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName)) {
                workbook.write(fileOutputStream);
                System.out.println("Dosya yazdırıldı.");
            }
        } catch (IOException e) {
            e.getMessage();
        }

        System.out.println(arrListLen);
    }
}
