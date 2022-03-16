package com.example.spring.data.api;

import com.example.spring.data.common.AuthorExportFileExcel;
import com.example.spring.data.common.HiredExportFileExcel;
import com.example.spring.data.common.ReadFileExcel;
import com.example.spring.data.model.Author;
import com.example.spring.data.model.Book;
import com.example.spring.data.model.MemberHired;
import com.example.spring.data.service.AuthorService;
import com.example.spring.data.service.BookService;
import com.example.spring.data.service.MemberService;
import com.example.spring.data.utils.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@Transactional
@CrossOrigin("*")
@RequestMapping(("/api/file"))
public class FileResource {
    @Autowired
    AuthorService authorService;

    @Autowired
    MemberService memberService;

    @Autowired
    BookService bookService;

    @PostMapping("/author/import")
    public ResponseEntity<?> importAuthorExcelFile(
            @RequestParam(name = "file", required = true)MultipartFile file
    ) throws IOException {
        List<Author> authors = ReadFileExcel.readFileAuthors(ConvertObject.convertMultipartToFile(file));
        List<Author> result = authorService.createAuthor(authors);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/author/export")
    public ResponseEntity<?> exportAuthorsToExcel(HttpServletResponse res) throws IOException {
        res.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=author_list.xlsx";

        res.setHeader(headerKey, headerValue);
        //data
        List<Author> authors = authorService.findAll();
        //call method export
        AuthorExportFileExcel authorExportFileExcel = new AuthorExportFileExcel(authors);
        authorExportFileExcel.export(res);


        return ResponseEntity.ok("Export success");
    }

    @GetMapping("/member-hired/export")
    public ResponseEntity<?> exportHiredToExcel(HttpServletResponse res) throws IOException {
        res.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=hired_list.xlsx";
        res.setHeader(headerKey, headerValue);
        //data
        List<MemberHired> memberHireds = memberService.findAllMemberHired();
        //call method export
        HiredExportFileExcel hiredExportFileExcel = new HiredExportFileExcel(memberHireds);
        hiredExportFileExcel.export(res);

        return ResponseEntity.ok("Export success");
    }

    @PostMapping("/book/import")
    public ResponseEntity<?> importBookExcelFile(
            @RequestParam(name = "file", required = true)MultipartFile file
    ) throws IOException {
        List<Book> books = ReadFileExcel.readFileBooks(ConvertObject.convertMultipartToFile(file));
        List<Book> result = bookService.createBook(books);
        return ResponseEntity.ok().body(result);
    }

}
