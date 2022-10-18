package com.lion.ebook.app.post.dto;

import com.lion.ebook.app.keyword.dto.KeywordDto;
import com.lion.ebook.app.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PostListDto {
    private Long id;
    private Member author;
    private String subject;
    private String modifiedAt;
    private String createdAt;

    private List<KeywordDto> keywordList;
}
