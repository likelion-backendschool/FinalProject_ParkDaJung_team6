package com.lion.ebook.post;

import com.lion.ebook.app.post.controller.PostController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
//@Transactional
public class PostControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PostController postController;

    @Test
    @DisplayName("mapstruct test")
    public void t1() throws Exception {
        ResultActions resultActions = mvc
                .perform(
                        MockMvcRequestBuilders.get("/post/list")
                )
                .andDo(print());

//        resultActions
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(attribute)
    }


    @Test
    @DisplayName("글 생성 테스트")
    public void t2() {

    }
}
