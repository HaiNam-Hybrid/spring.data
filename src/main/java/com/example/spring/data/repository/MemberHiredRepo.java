package com.example.spring.data.repository;

import com.example.spring.data.model.MemberHired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberHiredRepo extends JpaRepository<MemberHired, Long> {
    List<MemberHired> findByMemberIdCard(String idCard);
}
