package com.example.spring.data.service;

import com.example.spring.data.model.Member;
import com.example.spring.data.model.MemberHired;

import java.util.List;

public interface MemberService {

    Member findMember(String idCard);
    List<Member> findMemberNameLike(String name);
    List<Member> findAllMembers();
    Member updateMember(Member member);
    List<MemberHired> findMemberHiredBook(String memberIdCard);
    List<MemberHired> saveAll(List<MemberHired> memberHiredList);
    void giveBackBook(Long id);
}
