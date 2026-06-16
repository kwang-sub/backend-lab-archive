package com.example.chap11.service;

import com.example.chap11.domain.Address;
import com.example.chap11.domain.Member;
import com.example.chap11.exception.DuplicateMemberException;
import com.example.chap11.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 회원저장_테스트() {
        Member member = Member.builder()
                .name("kwang")
                .address(new Address("서울", "**동", "123"))
                .build();
        Long saveId = memberService.join(member);
        Member findMember = memberService.findOne(saveId);

        assertThat(findMember).isNotNull();
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 회원이름중복_예외발생() {
        Member member1 = Member.builder()
                .name("kwang")
                .address(new Address("서울", "**동", "123"))
                .build();
        memberService.join(member1);

        Member member2 = Member.builder()
                .name("kwang")
                .address(new Address("서울", "**동", "123"))
                .build();

        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(DuplicateMemberException.class);
    }
}