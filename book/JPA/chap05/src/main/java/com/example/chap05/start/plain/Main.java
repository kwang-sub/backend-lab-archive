package com.example.chap05.start.plain;

import com.example.chap05.start.plain.Member;
import com.example.chap05.start.plain.Team;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");
        Team team = new Team("team1", "팀1");
        member1.setTeam(team);
        member2.setTeam(team);

        log.info("member1.team = {}", member1.getTeam());
    }
}
