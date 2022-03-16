package com.example.spring.data.common;

import com.example.spring.data.model.Author;
import com.example.spring.data.model.Book;
import com.example.spring.data.model.Category;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ReadFileExcel {

    public static List<Author> readFileAuthors(File file) throws IOException {
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

    public static List<Book> readFileBooks(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet datatypeSheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        System.out.println("First cell is: {} " +firstCell.getStringCellValue());

        List<Book> books = new ArrayList<>();
        while(iterator.hasNext()) {
            Row curRow = iterator.next();
            String bookName = curRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            Number quantity = curRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
            Number price = curRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
            String authorName = curRow.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            String authorDescription = curRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            //if want import book more than 1 category, we will resolve this is list
            String categoryName = curRow.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

            Long qt = quantity.longValue();
            Long pr = price.longValue();
            Author author = new Author(authorName, authorDescription);
            Category category = new Category(categoryName);
            Set<Category> ctgr = new HashSet<>();
            ctgr.add(category);
            Book book = new Book(bookName, qt, pr, author, ctgr);
            books.add(book);
        }
        return books;
    }
}
