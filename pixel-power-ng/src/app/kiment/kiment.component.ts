import { Component } from '@angular/core';
import { Article, ServiceKimentService } from './service-kiment.service';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms'; // <== Import de FormsModule

@Component({
  selector: 'app-kiment',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './kiment.component.html',
  styleUrl: './kiment.component.css',
})
export class KimentComponent {
  liste: Article[] = [];
  prixTotal: number = 0;
  isFormVisible: boolean = true;

  constructor(private kimentService: ServiceKimentService) {}

  ngOnInit() {
    this.liste = this.kimentService.getListe();
    this.prixTotal = this.kimentService.getPrixTotal();
  }

  onClick() {
    console.log('Bouton cliqué !');
    alert('Bouton cliqué !');
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
      this.kimentService.addArticle(form.value);
      this.onAddArticle();
    }
  }
}
