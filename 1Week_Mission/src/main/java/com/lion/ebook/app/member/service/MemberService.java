package com.lion.ebook.app.member.service;

import com.lion.ebook.app.keyword.repository.KeywordRepository;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.repositoy.MemberRepository;
import com.lion.ebook.common.dto.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    public Member join(String username, String password, String email, String nickname) {
        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .authLevel(3)
                .build();

        return memberRepository.save(member);
    }

    public Member findById(long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public void modify(Member member, String email, String nickname) {
        member.setEmail(email);
        member.setNickname(nickname);
        memberRepository.save(member);
    }

    public boolean existsByUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
