package com.lion.ebook.app.email.service;

import com.lion.ebook.common.dto.ResultData;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender emailSender;

    @Value("${custom.email.sender}")
    private String sender;


    public ResultData sendEmail(String to, String title, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(title);
        message.setText(body);

        try {
            emailSender.send(message);
            return new ResultData("200", "성공적으로 발송했습니다.");
        } catch(MailException e) {
            log.debug(e.toString());
            return new ResultData("400", "이메일 발송 중 문제가 발생했습니다.");
        }
    }
}
