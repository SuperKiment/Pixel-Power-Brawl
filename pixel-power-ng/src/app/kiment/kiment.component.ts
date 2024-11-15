import { Component } from '@angular/core';
import { Article, ServiceKimentService } from './service-kiment.service';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms'; // <== Import de FormsModule
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-kiment',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  providers: [ServiceKimentService],
  templateUrl: './kiment.component.html',
  styleUrl: './kiment.component.css',
})
export class KimentComponent {
  liste: Article[] = [];
  prixTotal: number = 0;
  isFormVisible: boolean = true;

  constructor(private kimentService: ServiceKimentService) {}

  ngOnInit() {
    this.prixTotal = this.kimentService.getPrixTotal();
    this.updateListe();
  }

  updateListe() {
    this.liste = [];
    this.kimentService.getArticles().subscribe((data: any) => {
      for (let article of data) {
        console.log(article);

        this.liste.push({
          nom: article.libelle,
          quantite: article.quantity,
          prix: article.price,
          id: article.id,
        });
      }
    });
  }

  onClick() {
    console.log('Bouton cliqué !');
    alert('Bouton cliqué !');
  }

  onSupprimer(index: number) {
    this.kimentService.removeArticle(index).subscribe((data) => {
      console.log(data);
      this.updateListe();
    });
  }

  onPlusClick() {
    this.isFormVisible = !this.isFormVisible;
  }

  onAddArticle() {
    this.prixTotal = this.kimentService.getPrixTotal();
  }

  onSubmit(event: Event, form: NgForm) {
    event.preventDefault();

    if (form.value.nom != '') {
      this.kimentService.addArticle(form.value).subscribe((data) => {
        console.log(data);
      });
      this.onAddArticle();
    }
  }
}
