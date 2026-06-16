package com.example.chap11.repository;

import com.example.chap11.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface MemberRepository /*extends JpaRepository<Member, Long> */{
    List<Member> findByName(String name);
}
