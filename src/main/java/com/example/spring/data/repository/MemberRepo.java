package com.example.spring.data.repository;

import com.example.spring.data.model.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    Member findByIdCard(String idCard);

    List<Member> findByMemberNameLike(String name);
}
