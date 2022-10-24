package com.lion.ebook.app.base;

import com.lion.ebook.app.hashTag.entity.HashTag;
import com.lion.ebook.app.hashTag.service.HashTagService;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.service.MemberService;
import com.lion.ebook.app.post.entity.Post;
import com.lion.ebook.app.post.service.PostService;
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
    CommandLineRunner initData(MemberService memberService, PostService postService, HashTagService hashTagService) {
        //회원 데이터 생성
        Member member1 = memberService.join("1", "1234", "1@1.com", "유저1", false);
        Member member2 = memberService.join("2", "1234", "2@2.com", "유저2", false);

        Post post1 = postService.create(member1, "안녕1", "글1", "<h1>안녕1</h1>");
        Post post2 = postService.create(member2, "안녕2", "글2", "<h1>안녕2</h1>");

        hashTagService.create(member1, post1, "#친목");
        hashTagService.create(member1, post1, "#인사");

        hashTagService.create(member1, post2, "#친목");
        hashTagService.create(member2, post2, "#친목");

        return null;
    }
}
