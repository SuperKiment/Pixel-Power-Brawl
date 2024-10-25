import { Injectable } from '@angular/core';

export interface Article {
  nom: string;
  quantite: number;
  prix: number;
}

@Injectable({
  providedIn: 'root',
})
export class ServiceKimentService {
  private liste: Article[] = [{ nom: 'coucou', quantite: 5, prix: 6 }];

  constructor() {}

  getListe(): Article[] {
    return this.liste;
  }

  addArticle(article: Article): void {
    this.liste.push(article);
  }

  getPrixTotal(): number {
    let prixTotal: number = 0;

    for (let article of this.liste) {
      prixTotal += article.prix * article.quantite;
    }

    return prixTotal;
  }
}
