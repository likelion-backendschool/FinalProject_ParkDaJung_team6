package com.lion.ebook.app.hashTag.controller;

import com.lion.ebook.app.hashTag.service.HashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class HashTagController {
    private final HashTagService hashTagService;
}
