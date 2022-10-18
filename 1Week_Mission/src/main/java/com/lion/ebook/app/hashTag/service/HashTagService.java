package com.lion.ebook.app.hashTag.service;

import com.lion.ebook.app.hashTag.entity.HashTag;
import com.lion.ebook.app.hashTag.repository.HashTagRepository;
import com.lion.ebook.app.keyword.entity.Keyword;
import com.lion.ebook.app.keyword.repository.KeywordRepository;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HashTagService {
    private final HashTagRepository hashTagRepository;

    private final KeywordRepository keywordRepository;

    public HashTag create(Member member, Post post, String hashTagContent) {
        Keyword keyword = keywordRepository.findByHashTag(hashTagContent);

        if(keyword != null && hashTagRepository.existsByPostAndKeyword(post, keyword)) {
            return null;
        }

        if(keyword == null) {
            keyword = Keyword.builder().hashTag(hashTagContent).build();
            keywordRepository.save(keyword);
        }

        HashTag hashTag = HashTag
                .builder()
                .member(member)
                .post(post)
                .keyword(keyword)
                .build();

        return hashTagRepository.save(hashTag);
    }

    public List<Keyword> findByPostId(Long id) {
        return hashTagRepository.findByPostId(id);
    }
}
