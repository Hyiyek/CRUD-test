package com.example.member.repository;
import com.example.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository; // J를 대문자로 수정

public interface MemberRepository extends JpaRepository<Member, Long> { // J를 대문자로 수정
}