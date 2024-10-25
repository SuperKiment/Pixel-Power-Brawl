import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogContent } from '@angular/material/dialog';
import { MatDialogActions } from '@angular/material/dialog'; // Ensure to import this if needed
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms'
import { Article } from '../formulaire/articles.model';
import { ArticleService } from '../formulaire/article-service.service';

@Component({
  selector: 'app-popup-form',
  templateUrl: './popup-form.component.html',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogActions,
    MatDialogContent,
    FormsModule
  ],
})
export class PopupFormComponent {
  nomArticle: string = '';
  quantiteArticle: number = 0;
  prixArticle: number = 0;

  constructor(
    private dialogRef: MatDialogRef<PopupFormComponent>,
    private articleService: ArticleService
  ) {}

  onSubmit() {
    const newArticle: Article = {
      nomArticle: this.nomArticle,
      quantiteArticle: this.quantiteArticle,
      prixArticle: this.prixArticle

    }
    this.articleService.addArticles(newArticle);
    this.dialogRef.close();
  }

  onCancel() {
    this.dialogRef.close(); // Close the dialog without saving
  }
}
