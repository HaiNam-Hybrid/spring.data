package com.example.spring.data.repository;

import com.example.spring.data.model.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    Member findByIdCard(String idCard);

    List<Member> findByMemberNameLike(String name);

    Optional<Member> findByMemberName(String memberName);

    Boolean existsByMemberName(String memberName);

    Boolean existsByEmail(String email);
}
