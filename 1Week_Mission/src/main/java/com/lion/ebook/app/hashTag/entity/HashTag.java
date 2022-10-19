package com.lion.ebook.app.hashTag.entity;

import com.lion.ebook.app.keyword.entity.Keyword;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.post.entity.Post;
import com.lion.ebook.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static org.hibernate.annotations.CascadeType.DELETE;


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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;
}
