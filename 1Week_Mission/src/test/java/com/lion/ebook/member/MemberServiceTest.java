package com.lion.ebook.member;

import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.service.MemberService;
import com.lion.ebook.common.dto.ResultData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원 생성")
    void t1() {
        Member member1 = memberService.join("test1", "1234", "1@1.com", "유저1", false);

        assertThat(member1).isNotNull();
    }

    @Test
    @DisplayName("동일한 username을 가진 회원은 가입할 수 없음")
    void t2() {
        Member member1 = memberService.join("test1", "1234", "1@1.com", "유저1", false);
        Member member2 = memberService.join("test1", "1234", "2@2.com", "유저2", false);

        assertThat(member1).isNotNull();
        assertThat(member2).isNotNull();
    }

}
