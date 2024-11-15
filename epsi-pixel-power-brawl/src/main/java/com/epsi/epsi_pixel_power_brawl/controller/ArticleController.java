package com.epsi.epsi_pixel_power_brawl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.epsi.epsi_pixel_power_brawl.model.Article;
import com.epsi.epsi_pixel_power_brawl.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping
	public List<Article> getAll() {
		return articleService.findAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
		System.out.println("cringe" + id);
		boolean isRemoved = articleService.deleteById(id);
		if (!isRemoved) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public Article createArticle(@RequestBody Article article) {
		System.out.println("id : " + article.id);
		Article returnedArticle = articleService.add(article);
		System.out.println("return id : " + returnedArticle.id);
		return returnedArticle;
	}
}
