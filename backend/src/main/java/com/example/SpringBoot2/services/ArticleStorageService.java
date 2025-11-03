package com.example.SpringBoot2.services;

import org.springframework.stereotype.Service;

import com.example.SpringBoot2.models.Article;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

@Service
public class ArticleStorageService {

    // This volatile list will hold our articles in memory.
    // 'volatile' ensures that changes made by one thread are visible to others.
    private volatile List<Article> articles = Collections.synchronizedList(new ArrayList<>());

    /**
     * Retrieves the current list of articles.
     * 
     * @return A list of articles.
     */
    public List<Article> getArticles() {
        return this.articles;
    }

    /**
     * Replaces the old list with a new, updated list of articles.
     * 
     * @param newArticles The new list of articles to store.
     */
    public void setArticles(List<Article> newArticles) {
        this.articles = Collections.synchronizedList(new ArrayList<>(newArticles));
    }
}