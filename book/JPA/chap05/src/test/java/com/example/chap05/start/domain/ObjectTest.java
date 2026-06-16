package com.example.chap05.start.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ObjectTest {

    @Test
    void objectTest() {
        Team team = new Team("팀1");
        Member member = new Member("회원1");

        member.setTeam(team);

        assertThat(team.getMembers().size()).isEqualTo(1);
    }

    @Test
    void objectTest2() {
        Team team = new Team("팀1");
        Member member = new Member("회원1");

        member.setTeam(team);
        team.getMembers().add(member);

        assertThat(team.getMembers().size()).isEqualTo(2);

    }

}