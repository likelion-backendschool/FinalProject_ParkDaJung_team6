package com.lion.ebook.app.member.controller;

import com.lion.ebook.app.member.dto.JoinForm;
import com.lion.ebook.app.member.service.MemberService;
import com.lion.ebook.common.dto.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
