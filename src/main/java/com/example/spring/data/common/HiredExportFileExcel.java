package com.example.spring.data.common;


import com.example.spring.data.model.Author;
import com.example.spring.data.model.MemberHired;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HiredExportFileExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<MemberHired> memberHireds;

    public HiredExportFileExcel(List<MemberHired> memberHireds) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Hired_List");
        this.memberHireds = memberHireds;
    }

    public void writeHeader() {
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("ID Card");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(1);

        cell = row.createCell(2);
        cell.setCellValue("ID Book");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(2);

        cell = row.createCell(3);
        cell.setCellValue("Book Name");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(3);

        cell = row.createCell(4);
        cell.setCellValue("Quantity");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(4);
    }

    public void writeData() {
        int cnt = 1;
        for (MemberHired memberHired : memberHireds) {
            Row row = sheet.createRow(cnt);
            Cell cell = row.createCell(0);
            cell.setCellValue(memberHired.getId());
            cell = row.createCell(1);
            cell.setCellValue(memberHired.getMemberIdCard());
            cell = row.createCell(2);
            cell.setCellValue(memberHired.getBookId());
            cell = row.createCell(3);
            cell.setCellValue(memberHired.getBookName());
            cell = row.createCell(4);
            cell.setCellValue(memberHired.getQuantity());

            cnt++;
        }
    }

    public void export(HttpServletResponse res) throws IOException {
        writeHeader();
        writeData();

        ServletOutputStream outputStream = res.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
