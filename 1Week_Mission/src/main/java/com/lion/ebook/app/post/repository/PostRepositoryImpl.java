package com.lion.ebook.app.post.repository;

import com.lion.ebook.app.keyword.entity.QKeyword;
import com.lion.ebook.app.post.entity.Post;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.lion.ebook.app.post.entity.QPost.post;
import static com.lion.ebook.app.hashTag.entity.QHashTag.hashTag;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> listByHashTag(String hashTagStr) {
        return jpaQueryFactory
                .select(post)
                .distinct()
                .from(post)
                .innerJoin(hashTag)
                    .on(hashTag.post.eq(post))
                .where(eqHashTagStr(hashTagStr, hashTag.keyword))
                .fetch();
    }

    private BooleanExpression eqHashTagStr(String hashTagStr, QKeyword keyword) {
        if(hashTagStr == null || keyword == null) {
            return null;
        }

        return keyword.hashTag.eq(hashTagStr);
    }
}
