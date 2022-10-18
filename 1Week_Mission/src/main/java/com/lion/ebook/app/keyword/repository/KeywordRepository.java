package com.lion.ebook.app.keyword.repository;

import com.lion.ebook.app.hashTag.entity.HashTag;
import com.lion.ebook.app.keyword.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Keyword findByHashTag(String hashTag);

}
