package com.example.easykiosk.utils;

import com.example.easykiosk.model.UserDetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public static ByteArrayInputStream usersToExcel(List<UserDetails> userDetails) throws IOException {
        String[] COLUMNs = {"Id", "emailaddress", "imageurl", "password","phonenumber","token","username"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("UserDetails");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
//            CellStyle ageCellStyle = workbook.createCellStyle();
//            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (UserDetails userDetail : userDetails) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(userDetail.getId());
                row.createCell(1).setCellValue(userDetail.getEmailaddress());
                row.createCell(2).setCellValue(userDetail.getImageurl());
                row.createCell(3).setCellValue(userDetail.getPassword());
                row.createCell(4).setCellValue(userDetail.getPhonenumber());
                row.createCell(5).setCellValue(userDetail.getToken());
                row.createCell(6).setCellValue(userDetail.getUsername());

//                Cell ageCell = row.createCell(3);
//                ageCell.setCellValue(customer.getAge());
//                ageCell.setCellStyle(ageCellStyle);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}


