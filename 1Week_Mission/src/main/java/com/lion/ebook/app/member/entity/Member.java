package com.lion.ebook.app.member.entity;

import com.lion.ebook.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

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

    private int authLevel;
}
