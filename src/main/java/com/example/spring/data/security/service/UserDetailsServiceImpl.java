package com.example.spring.data.security.service;

import com.example.spring.data.model.Member;
import com.example.spring.data.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    MemberRepo memberRepo;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        Member member = memberRepo.findByMemberName(memberName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " +memberName));
        return UserDetailsImpl.build(member);
    }
}
