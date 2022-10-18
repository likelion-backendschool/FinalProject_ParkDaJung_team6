package com.lion.ebook.app.security.dto;


import com.lion.ebook.app.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MemberContext extends User {
    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;

    private final String nickname;
    private final String email;

    public MemberContext(Member member, List<GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.id = member.getId();
        this.createdAt = member.getCreatedAt();
        this.modifiedAt = member.getModifiedAt();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }

    public Member getMember() {
        return Member
                .builder()
                .id(id)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .username(username)
                .email(email)
                .nickname(nickname)
                .build();
    }

    public String getName() {
        return getUsername();
    }
}
