package com.example.spring.data.api;

import com.example.spring.data.model.*;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<List<Member>> searchMemberNameLike(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(memberService.findMemberNameLike(name), HttpStatus.OK) ;
    }

    @PostMapping("/member/create")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Member>> createMember(@RequestBody List<Member> members) {
        List<Member> result = memberService.createMember(members);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/member/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        //only current user edit current user's profile or edited by admin
        Member currentUser = memberService.getCurrentMember();
        Set<Role> roles = currentUser.getRoles();
        boolean match = roles.stream().map(Role::getName).collect(Collectors.toList()).contains(ERole.ROLE_ADMIN);
        if (match || currentUser.getId() == member.getId()) {
            Member result = memberService.updateMember(member);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

//    @PostMapping("/member/hired")
//    public ResponseEntity<List<MemberHired>> hiredBook(@RequestBody List<MemberHired> memberHiredList) {
//        List<MemberHired> result = memberService.saveAll(memberHiredList);
//        return ResponseEntity.ok().body(result);
//    }

    @GetMapping("/member/hired/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<List<MemberHired>> allHiredBook(@PathVariable(name ="id") String id) {
        List<MemberHired> result = memberService.findMemberHiredBook(id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/member/return/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
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
