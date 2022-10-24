package com.lion.ebook.email;

import com.lion.ebook.app.email.service.EmailService;
import com.lion.ebook.common.dto.ResultData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
//@Transactional
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    @DisplayName("이메일 발송 테스트")
    public void t1() {
        ResultData result = emailService.sendEmail("ekwjd990913@gmail.com", "테스트 제목", "테스트 본문");

        assertThat(result.getCode()).isEqualTo("200");
        assertThat(result.getMsg()).isEqualTo("성공적으로 발송했습니다.");
    }
}
