package com.example.spring.data.common;

import com.example.spring.data.model.Book;
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

public class BookExportFileExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Book> books;

    public BookExportFileExcel(List<Book> books) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Books_List");
        this.books = books;
    }

    public void writeHeader() {
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("Book Name");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("Quantity");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(1);

        cell = row.createCell(2);
        cell.setCellValue("Price");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(2);

        cell = row.createCell(3);
        cell.setCellValue("Author Name");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(3);

        cell = row.createCell(4);
        cell.setCellValue("Description");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(4);

        cell = row.createCell(5);
        cell.setCellValue("Category");
        cell.setCellStyle(style);
        sheet.autoSizeColumn(5);
    }

    public void writeData() {
        int cnt = 1;
        for (Book book : books) {
            Row row = sheet.createRow(cnt);
            Cell cell = row.createCell(0);
            cell.setCellValue(book.getBookName());
            cell = row.createCell(1);
            cell.setCellValue(book.getQuantity());
            cell = row.createCell(2);
            cell.setCellValue(book.getPrice());
            cell = row.createCell(3);
            cell.setCellValue(book.getAuthor().getAuthorName());
            cell = row.createCell(4);
            cell.setCellValue(book.getAuthor().getDescription());
            StringBuffer output = new StringBuffer(110);
            book.getCategories().stream().forEach(item ->{
                output.append(item.getCategoryName() +", ");
            });
            cell = row.createCell(5);
            cell.setCellValue(output.toString());
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
