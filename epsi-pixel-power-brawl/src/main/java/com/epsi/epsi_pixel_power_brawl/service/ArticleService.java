package com.epsi.epsi_pixel_power_brawl.service;

import com.epsi.epsi_pixel_power_brawl.repository.ArticleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsi.epsi_pixel_power_brawl.model.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	public boolean deleteById(Long id) {
		if (articleRepository.existsById(id)) {
			articleRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Article add(Article article) {
		return articleRepository.save(article);
	}
}
