package com.example.spring.data.api;

import com.example.spring.data.model.Book;
import com.example.spring.data.model.Member;
import com.example.spring.data.model.MemberHired;
import com.example.spring.data.repository.BookRepo;
import com.example.spring.data.repository.MemberHiredRepo;
import com.example.spring.data.service.BookService;
import com.example.spring.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberResource {
    @Autowired
    MemberService memberService;
    @Autowired
    BookService bookService;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    MemberHiredRepo memberHiredRepo;

    @GetMapping("/member/all")
    public ResponseEntity<List<Member>> getAllAuthor() {
        return new ResponseEntity<>(memberService.findAllMembers(), HttpStatus.OK) ;
    }

    @GetMapping("/member/{name}")
    public ResponseEntity<List<Member>> searchAuthorNameLike(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(memberService.findMemberNameLike(name), HttpStatus.OK) ;
    }

    @PostMapping("/member/create")
    public ResponseEntity<List<Member>> createMember(@RequestBody List<Member> members) {
        List<Member> result = memberService.createMember(members);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/member/update")
    public ResponseEntity<Member> updateAuthor(@RequestBody Member member) {
        Member result = memberService.updateMember(member);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/member/hired")
    public ResponseEntity<List<MemberHired>> hiredBook(@RequestBody List<MemberHired> memberHiredList) {
        List<MemberHired> result = memberService.saveAll(memberHiredList);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/member/hired/{id}")
    public ResponseEntity<List<MemberHired>> allHiredBook(@PathVariable(name ="id") String id) {
        List<MemberHired> result = memberService.findMemberHiredBook(id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/member/return/{id}")
    @Transactional
    public void  giveBackBook(@PathVariable(name = "id") Long id) {
        MemberHired memberHired = memberHiredRepo.findById(id).orElseThrow(()-> new NullPointerException("No record data"));
        Book book = bookRepo.findById(memberHired.getBookId()).orElseThrow(()-> new NullPointerException("No book in database"));
        book.setQuantity(book.getQuantity() + memberHired.getQuantity());
        bookService.updateBook(book);
        memberService.giveBackBook(id);
    }
}
