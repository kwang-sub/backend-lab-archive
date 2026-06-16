package com.example.chap11.datajpa;

import com.example.chap11.domain.Member;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class MemberRepository2Impl extends QuerydslRepositorySupport implements MemberRepository2Custom {

    public MemberRepository2Impl() {
        super(Member.class);
    }

    @Override
    public Member searchMember() {
        return null;
    }
}
