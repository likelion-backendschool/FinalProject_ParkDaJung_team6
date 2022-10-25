package com.lion.ebook.app.member.entity;

import com.lion.ebook.app.member.type.MemberType;
import com.lion.ebook.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    private String username;

    private String password;

    private String nickname;

    private String email;

    // 작가회원, 일반회원, 관리자
    @Enumerated(value = EnumType.STRING)
    private MemberType authLevel;

}
