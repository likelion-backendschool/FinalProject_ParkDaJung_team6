package com.lion.ebook.app.hashTag.repository;

import com.lion.ebook.app.hashTag.entity.HashTag;
import com.lion.ebook.app.keyword.entity.Keyword;
import com.lion.ebook.app.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long> {

    @Query("select t.keyword from HashTag t where t.post.id = :id")
    List<Keyword> findByPostId(Long id);

    boolean existsByPostAndKeyword(Post post, Keyword keyword);

    @Query("select t.keyword.hashTag from HashTag t where t.post.id = :id")
    List<String> findHashTagByPostId(Long id);
}
