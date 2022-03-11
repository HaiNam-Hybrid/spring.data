package com.example.spring.data.service;

import com.example.spring.data.model.Member;

public interface MemberService {

    Member findMember(String idCard);
}
