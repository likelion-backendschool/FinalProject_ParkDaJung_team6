package com.lion.ebook.app.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostModifyDto {
    private Long id;
    private Long authorId;
    private String author;
    private String subject;
    private String content;
    private List<String> hashTagList;
}
