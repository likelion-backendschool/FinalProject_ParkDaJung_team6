package com.lion.ebook.app.member.controller;

import com.lion.ebook.app.member.dto.JoinForm;
import com.lion.ebook.app.member.entity.Member;
import com.lion.ebook.app.member.service.MemberService;
import com.lion.ebook.common.dto.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String showJoin() {
        return "member/join";
    }

    @PostMapping("/join")
    public String doJoin(@Valid JoinForm joinForm) {
        ResultData result = memberService.join(joinForm.getUsername(), joinForm.getPassword(), joinForm.getEmail(), joinForm.getNickname());


        String resultMsg = "";
        try {
            resultMsg = URLEncoder.encode(result.getMsg(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        if(result.getCode().startsWith("2")) {
            return "redirect:/member/login?msg="+resultMsg;
        } else {
            return "redirect:/member/join?errorMsg="+resultMsg;
        }
    }

    @GetMapping("/login")
    public String showLogin() {
        return "member/login";
    }

    @GetMapping("/findid")
    @ResponseBody
    public ResultData<String> findId(@RequestParam() String email, Model model) {
        Member member = memberService.findByEmail(email);
        if(email == null || member == null) {
            return new ResultData<>("400", "이메일이 존재하지 않습니다.");
        }

        return new ResultData<>("200", "성공", member.getUsername());
    }

}
