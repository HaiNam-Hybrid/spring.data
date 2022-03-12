package com.example.spring.data.api;

import com.example.spring.data.model.Member;
import com.example.spring.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberResource {
    @Autowired
    MemberService memberService;

    @GetMapping("/member/all")
    public ResponseEntity<List<Member>> getAllAuthor() {
        return new ResponseEntity<>(memberService.findAllMembers(), HttpStatus.OK) ;
    }

    @GetMapping("/member/{name}")
    public ResponseEntity<List<Member>> searchAuthorNameLike(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(memberService.findMemberNameLike(name), HttpStatus.OK) ;
    }

    @PutMapping("/member/update")
    public ResponseEntity<Member> updateAuthor(@RequestBody Member member) {
        Member result = memberService.updateMember(member);
        return ResponseEntity.ok().body(result);
    }
}
