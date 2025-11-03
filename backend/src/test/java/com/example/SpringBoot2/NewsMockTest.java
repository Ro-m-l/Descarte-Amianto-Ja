package com.example.SpringBoot2;

import com.example.SpringBoot2.models.Article;
import com.example.SpringBoot2.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class NewsMockTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void testFakeArticlesInsertion() {
        articleRepository.deleteAll();

        Article a1 = new Article("Título 1", "https://link1.com", null, "conteúdo", null, null);
        Article a2 = new Article("Título 2", "https://link2.com", null, "conteúdo", null, null);

        articleRepository.save(a1);
        articleRepository.save(a2);

        var articles = articleRepository.findAll();
        assertEquals(2, articles.size());
    }
}