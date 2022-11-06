package xyz.zen.cms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.zen.cms.admin.model.dto.ArticleContentDto;
import xyz.zen.cms.admin.model.dto.ArticleInfoDto;
import xyz.zen.cms.admin.service.ArticleService;

import java.util.List;
import java.util.Optional;

@Controller
public class ArticleFrontController {

    private final ArticleService articleService;

    public ArticleFrontController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        List<ArticleInfoDto> all = articleService.getPublished();
        model.addAttribute("articles", all);
        return "index";
    }

    @GetMapping("/article/{path}")
    public String articlePage(@PathVariable String path, Model model) {
        Optional<ArticleContentDto> content = articleService.getContent(path);
        if (content.isEmpty()) {
            model.addAttribute("path", path);
            return "not_found";
        }
        model.addAttribute("content", content.get());
        return "article";
    }


}
