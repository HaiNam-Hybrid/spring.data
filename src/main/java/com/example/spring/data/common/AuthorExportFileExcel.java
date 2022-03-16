package com.example.spring.data.common;

import com.example.spring.data.model.Author;
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


public class AuthorExportFileExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Author> authors;

    public AuthorExportFileExcel() {
    }

    public AuthorExportFileExcel(List<Author> authors) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Authors_List");
        this.authors = authors;
    }


    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    private void writeHeader() {
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("Author ID");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("Author Name");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(1);

        cell = row.createCell(2);
        cell.setCellValue("Description");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(2);
    }

    //style của data có thể làm như header
    private void writeData() {
        int cnt = 1;
        for(Author author : authors) {
            Row row = sheet.createRow(cnt);
            Cell cell = row.createCell(0);
            cell.setCellValue(author.getId());
            cell = row.createCell(1);
            cell.setCellValue(author.getAuthorName());
            cell = row.createCell(2);
            cell.setCellValue(author.getDescription());

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
