
package com.example.SpringBoot2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBoot2.models.Article;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByLink(String link);
}