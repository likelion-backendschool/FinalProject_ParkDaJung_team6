package com.lion.ebook.hashTag;

import com.lion.ebook.app.hashTag.entity.HashTag;
import com.lion.ebook.app.hashTag.service.HashTagService;
import com.lion.ebook.app.keyword.entity.Keyword;
import com.lion.ebook.app.keyword.service.KeywordService;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.service.MemberService;
import com.lion.ebook.app.post.entity.Post;
import com.lion.ebook.app.post.service.PostService;
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
public class hashTagServiceTest {
    @Autowired
    HashTagService hashTagService;

    @Autowired
    MemberService memberService;

    @Autowired
    PostService postService;

    @Autowired
    KeywordService keywordService;

    @Test
    @DisplayName("해시태그 생성 테스트")
    public void t1() {
        Member member = memberService.findById(1L);
        Post post = postService.findById(1L);

        HashTag hashTag1 = hashTagService.create(member, post, "인사");

        assertThat(hashTag1).isNotNull();
        assertThat(hashTag1.getKeyword().getHashTag()).isEqualTo("인사");
    }

    @Test
    @DisplayName("동일한 해시태그 등록시 같은 키워드가 매핑되는지 테스트")
    public void t2() {
        Member member = memberService.findById(1L);
        Post post1 = postService.findById(1L);
        Post post2 = postService.findById(2L);

        HashTag hashTag1 = hashTagService.create(member, post1, "인사");
        HashTag hashTag2 = hashTagService.create(member, post2, "인사");

        assertThat(hashTag1.getKeyword()).isEqualTo(hashTag2.getKeyword());
        assertThat(hashTag1).isNotEqualTo(hashTag2);
    }
}
