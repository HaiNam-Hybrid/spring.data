package com.example.spring.data.api;

import com.example.spring.data.common.AuthorExportFileExcel;
import com.example.spring.data.common.ReadFileExcel;
import com.example.spring.data.model.Author;
import com.example.spring.data.service.AuthorService;
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

    @PostMapping("/author/import")
    public ResponseEntity<?> importExcelFile(
            @RequestParam(name = "file", required = true)MultipartFile file
    ) throws IOException {
        List<Author> authors = ReadFileExcel.readFile(ConvertObject.convertMultipartToFile(file));
        List<Author> result = authorService.createAuthor(authors);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/author/export")
    public ResponseEntity<?> exportToExcel(HttpServletResponse res) throws IOException {
        res.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=author_list.xlsx";

        res.setHeader(headerKey, headerValue);
        //data
        List<Author> authors = authorService.findAll();

        AuthorExportFileExcel authorExportFileExcel = new AuthorExportFileExcel(authors);
        authorExportFileExcel.export(res);


        return ResponseEntity.ok("Export success");
    }

}
