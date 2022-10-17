package com.lion.ebook.app.member.repositoy;

import com.lion.ebook.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

}
