package com.lion.ebook.app.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDetailDto {
    private Long id;
    private Long authorId;
    private String author;
    private String subject;
    private String contentHtml;
    private List<String> hashTagList;
}
