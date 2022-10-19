package com.lion.ebook.app.post.controller;


import com.lion.ebook.app.hashTag.entity.HashTag;
import com.lion.ebook.app.hashTag.service.HashTagService;
import com.lion.ebook.app.keyword.dto.KeywordDto;
import com.lion.ebook.app.keyword.entity.Keyword;
import com.lion.ebook.app.keyword.mapper.KeywordMapper;
import com.lion.ebook.app.post.dto.PostDetailDto;
import com.lion.ebook.app.post.dto.PostForm;
import com.lion.ebook.app.post.dto.PostListDto;
import com.lion.ebook.app.post.entity.Post;
import com.lion.ebook.app.post.mapper.PostMapper;
import com.lion.ebook.app.post.service.PostService;
import com.lion.ebook.app.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final HashTagService hashTagService;

    @GetMapping("/list")
    public String showList(Model model, String hashtag) {
        List<Post> postList = postService.listByHashTag(hashtag);
        List<PostListDto> postDetail = PostMapper.instance.toListDto(postList);

        for(PostListDto postListDto : postDetail) {
            List<Keyword> keywordList = hashTagService.findByPostId(postListDto.getId());
            List<KeywordDto> keywordDto = KeywordMapper.instance.toDtoList(keywordList);

            postListDto.setKeywordList(keywordDto);
        }

        model.addAttribute("postList", postDetail);
        return "post/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String getWrite() {
        return "post/write";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public String doWrite(@AuthenticationPrincipal MemberContext memberContext, @Valid PostForm postForm) {
        //TODO : 마크다운 원문과 렌더링 결과(HTML)까지 같이 저장한다.
        postService.create(memberContext.getMember(), postForm.getSubject(), postForm.getContent(), postForm.getContent(), postForm.getHashTagContents());


        return "redirect:/post/list";
    }

    @GetMapping("/{id}")
    public String getDetail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);

        if(post == null) {
            return null;
        }
        PostDetailDto postDetailDto = PostMapper.instance.toDetailDto(post);

        List<String> keywordList = hashTagService.findHashTagByPostId(id);
        postDetailDto.setHashTagList(keywordList);

        model.addAttribute("postDetail", postDetailDto);

        return "post/detail";
    }


}
