import { Injectable } from '@angular/core';
import { Article } from './articles.model';

@Injectable({
  providedIn: 'root'
})

export class ArticleService {
  private articles: Article[] = [];

  constructor() {
    this.loadArticles(); // Load articles from localStorage when service is created
  }

  private loadArticles() {
    const storedArticles = localStorage.getItem('articles');
    if (storedArticles) {
      this.articles = JSON.parse(storedArticles);
    }
  }

  private saveArticles() {
    localStorage.setItem('articles', JSON.stringify(this.articles));
  }
  // Method to add data
  addArticles(article: Article) {
    this.articles.push(article)
    this.saveArticles();
  }

    // Method to get all data
  getArticles(): Article[] {
      return this.articles;
  }

  deleteArticle(nomArticle: string) {
    this.articles = this.articles.filter(article => article.nomArticle != nomArticle);
  }

  // Method to clear all data
  clearArticles() {
    this.articles = [];
  }

}
