package com.lion.ebook.app.hashTag.entity;

import com.lion.ebook.app.keyword.entity.Keyword;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.post.entity.Post;
import com.lion.ebook.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"post_id", "keyword_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class HashTag extends BaseEntity {
    @ManyToOne
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;
}
