package com.lion.ebook.app.post.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostForm {

    @NotEmpty
    private String subject;

    @NotEmpty
    private String content;

    @NotEmpty
    private String contentHtml;

    private String hashTagContents;
}
