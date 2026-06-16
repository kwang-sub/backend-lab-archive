package com.example.chap11.datajpa;


import com.example.chap11.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository2 extends JpaRepository<Member, Long>, MemberRepository2Custom {

    @Query("select m from Member m where m.name = :name")
    Member findByName(@Param("name") String name);
}
