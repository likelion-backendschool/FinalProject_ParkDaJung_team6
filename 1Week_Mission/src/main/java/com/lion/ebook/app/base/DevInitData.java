package com.lion.ebook.app.base;

import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.service.MemberService;
import com.lion.ebook.common.dto.ResultData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevInitData {
    @Bean
    CommandLineRunner initData(MemberService memberService) {
        //회원 데이터 생성
        ResultData<Member> member1 = memberService.join("1", "1234", "1@1.com", "유저1");
        ResultData<Member> member2 = memberService.join("2", "1234", "2@2.com", "유저2");

        return null;
    }
}
