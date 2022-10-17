package com.lion.ebook.app.product.entity;

import com.lion.ebook.app.keyword.entity.Keyword;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Product extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="author_id")
    private Member author;

    @ManyToOne
    @JoinColumn(name="keyword_id")
    private Keyword keyword;

    private String content;

    private int price;
}
