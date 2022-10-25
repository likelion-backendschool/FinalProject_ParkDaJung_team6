package com.lion.ebook.app.security.service;


import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.repositoy.MemberRepository;
import com.lion.ebook.app.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        log.debug("[CustomUserDetailService] authLevel: "+member.getAuthLevel().name());
        authorities.add(new SimpleGrantedAuthority(member.getAuthLevel().name()));

        return new MemberContext(member, authorities);
    }
}
