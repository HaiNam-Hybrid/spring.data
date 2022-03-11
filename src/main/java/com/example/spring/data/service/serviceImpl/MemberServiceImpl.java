package com.example.spring.data.service.serviceImpl;

import com.example.spring.data.model.Member;
import com.example.spring.data.repository.MemberRepo;
import com.example.spring.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepo memberRepo;
    @Override
    public Member findMember(String idCard) {
        return memberRepo.findByIdCard(idCard);
    }
}
