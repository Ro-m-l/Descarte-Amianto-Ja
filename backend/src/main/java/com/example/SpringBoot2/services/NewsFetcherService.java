package com.example.SpringBoot2.services;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.SpringBoot2.models.Article;
import com.example.SpringBoot2.repositories.ArticleRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Component
public class NewsFetcherService {
    private final FeedParser feedParser;
    private final ArticleRepository articleRepository;
    private final WebScrapingService webScrapingService;
    private final String[] RSS_URL;

    @Autowired
    public NewsFetcherService(FeedParser feedParser, WebScrapingService webScrapingService,
            ArticleRepository articleRepository) {
        this.feedParser = feedParser;
        this.articleRepository = articleRepository;
        this.webScrapingService = webScrapingService;
        this.RSS_URL = new String[] {
                "https://www.google.com/alerts/feeds/13084519227054492460/4396369615713154359",
                "https://www.google.com/alerts/feeds/13084519227054492460/2537531881959246548" };
    }

    private boolean isTestProfileActive() {
        return "test".equals(System.getProperty("spring.profiles.active"));
    }

    @PostConstruct
    public void init() {
        if (!isTestProfileActive()) {
            fetchNewsFeed();
        }
    }

    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void fetchNewsFeed() {
        if (isTestProfileActive()) {
            return; // não roda durante os testes
        }
        System.out.println("Buscando últimas notícias do feed RSS...");
        RestTemplate restTemplate = new RestTemplate();

        for (String feedUrl : this.RSS_URL) {
            try {
                System.out.println("Processing feed: " + feedUrl);
                String xmlPayload;

                if (feedUrl.startsWith("http")) {
                    System.out.println("Fetching news from URL: " + feedUrl);
                    xmlPayload = restTemplate.getForObject(feedUrl, String.class);
                } else {
                    System.out.println("Reading news from local file: " + feedUrl);
                    xmlPayload = Files.readString(Paths.get(feedUrl));
                }

                List<Article> parsedArticles = feedParser.parse(xmlPayload);
                System.out.println("Parsed " + parsedArticles.size() + " articles from this feed.");

                parsedArticles.forEach(parsedArticle -> {
                    String actualLink = extractActualUrl(parsedArticle.getLink());

                    if (articleRepository.findByLink(actualLink).isEmpty()) {
                        System.out.println("New article found, scraping: " + actualLink);
                        ScrapedData dados = webScrapingService.ScrapeArticleData(actualLink);

                        Article articleToSave = new Article(
                                parsedArticle.getTitle(),
                                actualLink,
                                parsedArticle.getPublishDate(),
                                parsedArticle.getContent(),
                                dados.imageUrl(),
                                dados.pageTitle());

                        articleRepository.save(articleToSave);
                    }
                });
            } catch (Exception e) {
                System.err.println("ERROR: Failed to process feed " + feedUrl + " - " + e.getMessage());
            }
        }
        System.out.println("Finished processing all news feeds.");
    }

    private String extractActualUrl(String googleUrl) {
        if (googleUrl == null || !googleUrl.startsWith("https://www.google.com/url")) {
            return googleUrl;
        }
        try {
            String query = new java.net.URL(googleUrl).getQuery();
            String[] params = query.split("&");
            for (String param : params) {
                String[] pair = param.split("=");
                String key = pair[0];
                if ("url".equals(key)) {
                    String value = pair[1];
                    return URLDecoder.decode(value, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: Could not parse redirect URL: " + googleUrl + " - " + e.getMessage());
        }
        return googleUrl;
    }

    @Transactional
    public void fetchNewsFeedForTest() { // mesma lógica de fetchNewsFeed, mas sem if(isTestProfileActive()). usado para
                                         // testes
        System.out.println("Buscando últimas notícias do feed RSS...");
        // RestTemplate can be created once outside the loop
        RestTemplate restTemplate = new RestTemplate();

        for (String feedUrl : this.RSS_URL) {
            try {
                System.out.println("Processing feed: " + feedUrl);
                String xmlPayload;

                if (feedUrl.startsWith("http")) {
                    System.out.println("Fetching news from URL: " + feedUrl);
                    xmlPayload = restTemplate.getForObject(feedUrl, String.class);
                } else {
                    System.out.println("Reading news from local file: " + feedUrl);
                    xmlPayload = Files.readString(Paths.get(feedUrl));
                }

                List<Article> parsedArticles = feedParser.parse(xmlPayload);
                System.out.println("Parsed " + parsedArticles.size() + " articles from this feed.");

                parsedArticles.forEach(parsedArticle -> {
                    String actualLink = extractActualUrl(parsedArticle.getLink());

                    if (articleRepository.findByLink(actualLink).isEmpty()) {
                        System.out.println("New article found, scraping: " + actualLink);
                        ScrapedData dados = webScrapingService.ScrapeArticleData(actualLink);

                        Article articleToSave = new Article(
                                parsedArticle.getTitle(),
                                actualLink,
                                parsedArticle.getPublishDate(),
                                parsedArticle.getContent(),
                                dados.imageUrl(),
                                dados.pageTitle());

                        articleRepository.save(articleToSave);
                    }
                });
            } catch (Exception e) {
                System.err.println("ERROR: Failed to process feed " + feedUrl + " - " + e.getMessage());
            }
        }
        System.out.println("Finished processing all news feeds.");
    }

}