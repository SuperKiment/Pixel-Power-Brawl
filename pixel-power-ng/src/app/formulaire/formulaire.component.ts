import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupFormComponent } from '../popup-form/popup-form.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ArticleService } from './article-service.service';
import { Article } from './articles.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-formulaire',
  standalone: true,
  imports: [
    PopupFormComponent,
    MatDialogModule,
    CommonModule
  ],
  templateUrl: './formulaire.component.html',
  styleUrl: './formulaire.component.css',
})
export class FormulaireComponent {
  articles: Article[] = [];

  constructor(private dialog: MatDialog, private articleService: ArticleService) {}

  ngOnInit(){
    this.articles = this.articleService.getArticles();
  }

  openPopupForm() {
    const dialogRef = this.dialog.open(PopupFormComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      this.articles = this.articleService.getArticles();
    });
  }

  deleteArticle(nomArticle: string) {
    this.articleService.deleteArticle(nomArticle);
    this.articles = this.articleService.getArticles();
  }

  getTotalPriceFromArticle(article: Article){
    return article.quantiteArticle * article.prixArticle
  }

  getTotalPriceAllArticles(){
    let somme = 0;
    for (let article of this.articles){
      somme = somme + this.getTotalPriceFromArticle(article);
    }
    return somme;
  }
}
