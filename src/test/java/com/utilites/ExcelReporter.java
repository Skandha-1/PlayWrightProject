package com.utilites;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelReporter {
    private static final String FILE_NAME = "ExcelReport.xlsx";
    private static final String SHEET_NAME = "Test Results";
    private static Workbook workbook;
    private static Sheet sheet;

    static {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet(SHEET_NAME);
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet(SHEET_NAME);
                Row header = sheet.createRow(0);
                String[] columns = {"Test Name", "Status", "Error Message", "Duration (ms)", "Screenshot Path"};
                for (int i = 0; i < columns.length; i++) {
                    Cell cell = header.createCell(i);
                    cell.setCellValue(columns[i]);
                    CellStyle style = workbook.createCellStyle();
                    Font font = workbook.createFont();
                    font.setBold(true);
                    font.setColor(IndexedColors.WHITE.getIndex());
                    style.setFont(font);
                    style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(style);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logResult(String testName, String status, String errorMessage, long duration, String screenshotPath) {
        int lastRow = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(lastRow);
        row.createCell(0).setCellValue(testName);
        row.createCell(1).setCellValue(status);
        row.createCell(2).setCellValue(errorMessage != null ? errorMessage : "");
        row.createCell(3).setCellValue(duration);
        row.createCell(4).setCellValue(screenshotPath != null ? screenshotPath : "");
    }

    public static void saveReport() {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}