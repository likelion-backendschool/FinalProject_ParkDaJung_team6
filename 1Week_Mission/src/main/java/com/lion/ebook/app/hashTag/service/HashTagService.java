package com.lion.ebook.app.hashTag.service;

import com.lion.ebook.app.hashTag.entity.HashTag;
import com.lion.ebook.app.hashTag.repository.HashTagRepository;
import com.lion.ebook.app.keyword.entity.Keyword;
import com.lion.ebook.app.keyword.repository.KeywordRepository;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HashTagService {
    private final HashTagRepository hashTagRepository;

    private final KeywordRepository keywordRepository;

    public List<HashTag> create(Member member, Post post, String hashTagContent) {
        List<String> keywordList = Arrays.stream(hashTagContent.split("#"))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());

        List<HashTag> hashTagList = new ArrayList<>();

        for(String keywordStr : keywordList) {
            Keyword keyword = keywordRepository.findByHashTag(keywordStr);

            if(keyword != null && hashTagRepository.existsByPostAndKeyword(post, keyword)) {
                continue;
            }

            if(keyword == null) {
                keyword = Keyword.builder().hashTag(keywordStr).build();
                keywordRepository.save(keyword);
            }

            HashTag hashTag = HashTag
                    .builder()
                    .member(member)
                    .post(post)
                    .keyword(keyword)
                    .build();

            hashTagList.add(hashTag);
        }

        return hashTagRepository.saveAll(hashTagList);
    }

    public List<Keyword> findByPostId(Long id) {
        return hashTagRepository.findByPostId(id);
    }
}
