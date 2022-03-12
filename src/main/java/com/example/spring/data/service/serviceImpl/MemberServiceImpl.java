package com.example.spring.data.service.serviceImpl;

import com.example.spring.data.model.Member;
import com.example.spring.data.repository.MemberRepo;
import com.example.spring.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepo memberRepo;
    @Override
    public Member findMember(String idCard) {
        return memberRepo.findByIdCard(idCard);
    }

    @Override
    public List<Member> findMemberNameLike(String name) {
        return memberRepo.findByMemberNameLike("%"+name+"%");
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Member updateMember(Member member) {
        return memberRepo.save(member);
    }
}
