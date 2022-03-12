package com.example.spring.data.service;

import com.example.spring.data.model.Member;

import java.util.List;

public interface MemberService {

    Member findMember(String idCard);
    List<Member> findMemberNameLike(String name);
    List<Member> findAllMembers();
    Member updateMember(Member member);
}
