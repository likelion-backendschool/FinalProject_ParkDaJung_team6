package com.lion.ebook.app.post.service;

import com.lion.ebook.app.hashTag.service.HashTagService;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.post.entity.Post;
import com.lion.ebook.app.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final HashTagService hashTagService;

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post create(Member author, String subject, String content, String contentHtml) {
        Post post = Post.builder()
                .subject(subject)
                .content(content)
                .author(author)
                .contentHtml(contentHtml)
                .build();

        return postRepository.save(post);
    }

    public Post create(Member author, String subject, String content, String contentHtml, String hashTagStr) {
        Post post = create(author, subject, content, contentHtml);

        hashTagService.create(author, post, hashTagStr);

        return post;
    }

    public Post findById(long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
