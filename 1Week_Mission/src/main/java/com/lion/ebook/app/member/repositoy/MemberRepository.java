package com.lion.ebook.app.member.repositoy;

import com.lion.ebook.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(String username);

    Member findByUsername(String username);
}
