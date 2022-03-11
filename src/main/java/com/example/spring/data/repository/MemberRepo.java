package com.example.spring.data.repository;

import com.example.spring.data.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    Member findByIdCard(String idCard);

}
