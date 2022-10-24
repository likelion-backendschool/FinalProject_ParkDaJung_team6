package com.lion.ebook.app.member.service;

import com.lion.ebook.app.email.service.EmailService;
import com.lion.ebook.app.keyword.repository.KeywordRepository;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.repositoy.MemberRepository;
import com.lion.ebook.common.dto.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final EmailService emailService;

    @Transactional
    public Member join(String username, String password, String email, String nickname, boolean sendEmail) {

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .authLevel(3)
                .build();

        if(sendEmail) {
            emailService.sendEmail(email, "[멋북스] 회원가입을 축하합니다.", "가입해주셔서 감사합니다.");
        }

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
