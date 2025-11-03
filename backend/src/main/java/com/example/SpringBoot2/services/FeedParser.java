package com.example.SpringBoot2.services;

import com.example.SpringBoot2.models.Article;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeedParser {
    public List<Article> parse(String xmlPayload) {
        List<Article> articleList = new ArrayList<>();
        try (StringReader reader = new StringReader(xmlPayload)) {
            SyndFeed feed = new SyndFeedInput().build(reader);
            for (SyndEntry entry : feed.getEntries()) {
                String title = entry.getTitle();
                String link = entry.getLink();
                Date publishedDate = entry.getPublishedDate();
                String content = entry.getContents().isEmpty() ? "" : entry.getContents().get(0).getValue();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(publishedDate);

                String cleanContent = content.replaceAll("<[^>]*>", "").replaceAll("&nbsp;...", "...");

                articleList.add(new Article(title, link, formattedDate, cleanContent.strip(), null, null));
            }
        } catch (Exception e) {
            System.err.println("Error parsing XML payload: " + e.getMessage());
            e.printStackTrace();
        }
        return articleList;
    }
}