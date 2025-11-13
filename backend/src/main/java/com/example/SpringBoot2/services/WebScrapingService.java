package com.example.SpringBoot2.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class WebScrapingService {

    private static final String DEFAULT_IMAGE_URL = "";

    public ScrapedData ScrapeArticleData(String articleUrl) {
        String pageTitle = "Fonte Desconhecida";
        try {
            Document doc = Jsoup.connect(articleUrl)
                    .userAgent(
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get();

            Elements siteNameElements = doc.select("meta[property=og:site_name]");
            if (!siteNameElements.isEmpty()) {
                pageTitle = siteNameElements.first().attr("content");
            } else {
                // 2. Fallback: Parse the domain from the URL.
                URL url = new URL(articleUrl);
                pageTitle = url.getHost().replace("www.", ""); // e.g., "g1.globo.com"
            }
            // 2. The existing logic to find the image URL
            String imageUrl = DEFAULT_IMAGE_URL; // Start with the default
            Elements metaOgImage = doc.select("meta[property=og:image]");
            if (!metaOgImage.isEmpty()) {
                imageUrl = metaOgImage.first().attr("content");
            } else {
                Elements articleImages = doc.select("article img, .post-content img, .entry-content img");
                if (!articleImages.isEmpty()) {
                    imageUrl = articleImages.first().absUrl("src");
                }
            }

            // 3. Return a new ScrapedData object with our findings
            return new ScrapedData(pageTitle, imageUrl);

        } catch (IOException e) {
            System.err.println("ERROR scraping URL " + articleUrl + ": " + e.getMessage());
            // On error, return the original title and the default placeholder image
            return new ScrapedData(null, DEFAULT_IMAGE_URL);
        }
    }
}