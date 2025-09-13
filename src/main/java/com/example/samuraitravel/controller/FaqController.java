package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samuraitravel.entity.Faq;
import com.example.samuraitravel.service.FaqService;

@Controller
public class FaqController {

    private final FaqService faqService;

    // コンストラクタインジェクション
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/faqs")
    public String index(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model) {

        // 1ページにつき5件表示
        PageRequest pageable = PageRequest.of(page, 5);

        Page<Faq> faqs;
        if (keyword != null && !keyword.isEmpty()) {
            // キーワード検索あり
            faqs = faqService.findAllFaqs(keyword, pageable);
        } else {
            // 全件取得
            faqs = faqService.getAllFaqs(pageable);
        }

        // Viewに渡す
        model.addAttribute("faqs", faqs);
        model.addAttribute("keyword", keyword);

        return "user/faq";  // src/main/resources/templates/user/faq.html
    }
}
