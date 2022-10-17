package com.lion.ebook.app.member.service;

import com.lion.ebook.app.member.repositoy.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


}
