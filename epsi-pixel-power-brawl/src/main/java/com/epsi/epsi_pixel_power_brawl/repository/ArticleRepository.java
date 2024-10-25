package com.epsi.epsi_pixel_power_brawl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsi.epsi_pixel_power_brawl.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

}
