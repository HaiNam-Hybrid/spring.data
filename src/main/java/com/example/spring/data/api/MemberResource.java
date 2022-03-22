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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<List<Member>> getAllMember() {
        return new ResponseEntity<>(memberService.findAllMembers(), HttpStatus.OK) ;
    }

    @GetMapping("/member/{name}")
    public ResponseEntity<List<Member>> searchMemberNameLike(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(memberService.findMemberNameLike(name), HttpStatus.OK) ;
    }

    @PostMapping("/member/create")
    public ResponseEntity<List<Member>> createMember(@RequestBody List<Member> members) {
        List<Member> result = memberService.createMember(members);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/member/update")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        Member result = memberService.updateMember(member);
        return ResponseEntity.ok().body(result);
    }

//    @PostMapping("/member/hired")
//    public ResponseEntity<List<MemberHired>> hiredBook(@RequestBody List<MemberHired> memberHiredList) {
//        List<MemberHired> result = memberService.saveAll(memberHiredList);
//        return ResponseEntity.ok().body(result);
//    }

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
//dung ra la chi request bookID, cac thong tin con lai se lay cua user dang login trong he thong
//tam thoi se request ca body member hired de test api
    @PostMapping("/member/request/hired")
    public ResponseEntity<MemberHired> requestHireBook(@RequestBody MemberHired memberHired) {
        MemberHired result = memberService.requestHireBook(memberHired);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/admin/approve/request/{id}")
    @Transactional
    public ResponseEntity<?> approveRequest(@PathVariable(name = "id") Long id) {
        Instant startTime =Instant.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now().atZone(ZoneOffset.UTC))+"T00:00:00.883Z");
        Instant endTime = Instant.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now().atZone(ZoneOffset.UTC).plusDays(7).toInstant())+"T23:59:59.883Z");
        MemberHired memberHired = memberService.findRecordById(id);
        memberHired.setStartTimeHired(startTime);
        memberHired.setEndTimeHired(endTime);
        memberHired.setForfeit(0l);
        Long price = bookRepo.getById(memberHired.getBookId()).getPrice();
        memberHired.setUnitPrice(price);
        MemberHired result = memberService.approved(memberHired);
        return ResponseEntity.ok().body(result);
    }
}
