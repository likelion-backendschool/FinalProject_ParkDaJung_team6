package com.lion.ebook.app.post.controller;


import com.lion.ebook.app.hashTag.service.HashTagService;
import com.lion.ebook.app.keyword.dto.KeywordDto;
import com.lion.ebook.app.keyword.entity.Keyword;
import com.lion.ebook.app.keyword.mapper.KeywordMapper;
import com.lion.ebook.app.post.dto.PostDetailDto;
import com.lion.ebook.app.post.dto.PostListDto;
import com.lion.ebook.app.post.entity.Post;
import com.lion.ebook.app.post.mapper.PostMapper;
import com.lion.ebook.app.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final HashTagService hashTagService;

    @GetMapping("/list")
    public String showList(Model model) {
        List<Post> postList = postService.findAll();
        List<PostListDto> postDetail = PostMapper.instance.toListDto(postList);

        for(PostListDto postListDto : postDetail) {
            List<Keyword> keywordList = hashTagService.findByPostId(postListDto.getId());
            List<KeywordDto> keywordDto = KeywordMapper.instance.toDtoList(keywordList);

            postListDto.setKeywordList(keywordDto);
        }

        model.addAttribute("postList", postDetail);
        return "post/list";
    }
}
