package com.example.SpringBoot2;

import com.example.SpringBoot2.repositories.ArticleRepository;
import com.example.SpringBoot2.services.NewsFetcherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class NewsIntegrationTest {

    @Autowired
    private NewsFetcherService newsFetcherService;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void testFetchNewsFeedWorks() {
        // Limpa artigos antigos
        articleRepository.deleteAll();

        // Executa manualmente (não vai depender do @PostConstruct)
        newsFetcherService.fetchNewsFeedForTest();

        // Verifica se inseriu algo
        var articles = articleRepository.findAll();

        System.out.println("Total de artigos salvos: " + articles.size());
        articles.stream().limit(5).forEach(a ->
            System.out.println(a.getTitle() + " | " + a.getLink())
        );

        assertTrue(articles.size() > 0, "Nenhum artigo foi salvo!");
    }
}