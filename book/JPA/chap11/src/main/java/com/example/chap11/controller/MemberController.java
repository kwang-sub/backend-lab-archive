package com.example.chap11.controller;

import com.example.chap11.domain.Member;
import com.example.chap11.domain.QMember;
import com.example.chap11.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.chap11.domain.QMember.member;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/{memberId}/edit")
    public String memberUpdateForm(@PathVariable("memberId") Long memberId, Model model) {
        Member member = memberService.findOne(memberId);
        model.addAttribute("member", member);
        return "members/memberSaveForm";
    }

    @PostMapping("/member/{memberId}/edit")
    public String memberUpdate(@ModelAttribute("member") Member member) {
        System.out.println("member : " + member);
        return "members/memberSaveForm";
    }

    @GetMapping("/members/new")
    public String createMemberForm(@ModelAttribute("member") Member member) {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(@ModelAttribute("member") Member member) {
        memberService.join(member);
        return "redirect:/";
    }
}
