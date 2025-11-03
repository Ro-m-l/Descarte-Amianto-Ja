package com.example.SpringBoot2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBoot2.models.Article;
import com.example.SpringBoot2.repositories.ArticleRepository;
import com.example.SpringBoot2.services.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final ArticleRepository articleRepository;
    private final NewsFetcherService newsFetcherService;

    @Autowired
    public NewsController(ArticleRepository articleRepository, NewsFetcherService newsFetcherService) {
        this.articleRepository = articleRepository;
        this.newsFetcherService = newsFetcherService;
    }

    @GetMapping
    public List<Article> getNews() {
        return articleRepository.findAll();
    }
}