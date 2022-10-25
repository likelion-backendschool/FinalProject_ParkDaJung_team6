package com.lion.ebook.app.member.controller;

import com.lion.ebook.app.email.service.EmailService;
import com.lion.ebook.app.member.dto.JoinForm;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.service.MemberService;
import com.lion.ebook.app.security.dto.MemberContext;
import com.lion.ebook.common.dto.ResultData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    private final EmailService emailService;

    @GetMapping("/join")
    public String showJoin() {
        return "member/join";
    }

    @PostMapping("/join")
    public String doJoin(@Valid JoinForm joinForm, Model model, RedirectAttributes redirectAttributes) {
        if(memberService.existsByUsername(joinForm.getUsername())) {
            model.addAttribute("errorMsg", "아이디가 이미 존재합니다.");
            return "member/join";
        }

        memberService.join(joinForm.getUsername(), joinForm.getPassword(), joinForm.getEmail(), joinForm.getNickname(), true);
        redirectAttributes.addFlashAttribute("successMsg", "회원가입에 성공했습니다.");

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "member/login";
    }

    @GetMapping("/findUsername")
    @ResponseBody
    public ResultData<String> findId(@RequestParam(required = true) String email, Model model) {
        Member member = memberService.findByEmail(email);
        if(email == null || member == null) {
            return new ResultData<>("400", "이메일이 존재하지 않습니다.");
        }

        return new ResultData<>("200", "성공", member.getUsername());
    }

    @PostMapping("/findPassword")
    @ResponseBody
    public ResultData<String> findPassword(@RequestParam(required = true) String email, String username, Model model) {
        log.debug("email:%s, username:%s\n".formatted(email, username));
        Member member = memberService.findByEmailAndUsername(email, username);

        if(member == null) {
            return new ResultData<>("400", "해당 회원이 존재하지 않습니다.");
        }
        memberService.changeTmpPw(member);

        return new ResultData<>("200", "성공", member.getUsername());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getMypage() {
        return "member/profile";
    }

    @GetMapping("/modify")
    @PreAuthorize("isAuthenticated()")
    public String getModify(Principal principal) {
        return "/member/modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify")
    public String modify(@AuthenticationPrincipal MemberContext context, String email, String nickname, RedirectAttributes redirectAttributes) {
        if(memberService.existsByEmail(email)) {
            redirectAttributes.addFlashAttribute("errorMsg", "아이디가 이미 존재합니다.");
            return "redirect:/member/profile";
        }

        Member member = memberService.findById(context.getId());
        memberService.modify(member, email, nickname);

        context.setModifiedAt(member.getModifiedAt());
        context.setEmail(member.getEmail());
        context.setNickname(member.getNickname());
        Authentication authentication = new UsernamePasswordAuthenticationToken(context, member.getPassword(), context.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/member/profile";
    }
}
