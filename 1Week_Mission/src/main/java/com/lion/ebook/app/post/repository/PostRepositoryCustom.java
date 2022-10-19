package com.lion.ebook.app.post.repository;

import com.lion.ebook.app.post.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> listByHashTag(String hashTag);
}
