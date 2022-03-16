package com.example.spring.data.common;

import com.example.spring.data.model.Author;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadFileExcel {
    public static List<Author> readFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet datatypeSheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        System.out.println("First cell is: {} " +firstCell.getStringCellValue());

        List<Author> authors = new ArrayList<>();
        while(iterator.hasNext()) {
            Row curRow = iterator.next();
            Author author = new Author(
                    curRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                    curRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()
            );
            authors.add(author);
        }
        return authors;
    }
}
