package com.lion.ebook.app.security.dto;


import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.type.MemberType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MemberContext extends User {
    private final Long id;
    private final LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
    private final String username;

    private String nickname;

    private String email;

    private String authLevel;

    public MemberContext(Member member, List<GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.id = member.getId();
        this.createdAt = member.getCreatedAt();
        this.modifiedAt = member.getModifiedAt();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.authLevel = member.getAuthLevel().name();
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
                .authLevel(MemberType.valueOf(authLevel))
                .build();
    }

    public String getName() {
        return getUsername();
    }

}
