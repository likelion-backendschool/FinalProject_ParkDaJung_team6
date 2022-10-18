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


    public ResultData<Member> join(String username, String password, String email, String nickname) {
        if(memberRepository.existsByUsername(username)) {
            return new ResultData("400", "동일한 아이디의 유저가 존재합니다.", "");
        }

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .authLevel(3)
                .build();

        memberRepository.save(member);

        return new ResultData("201", "회원가입이 완료되었습니다.", member);
    }

    public Member findById(long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }
}
