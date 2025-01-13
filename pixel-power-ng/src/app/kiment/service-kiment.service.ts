import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import fetchAuth from '../utils/fetch-auth';

export interface Article {
  nom: string;
  quantite: number;
  prix: number;
  id: number;
}

@Injectable({
  providedIn: 'root',
})
export class ServiceKimentService {
  private liste: Article[] = [];
  private urlAPI: string = 'http://localhost:8081';

  constructor(private httpClient: HttpClient) {}

  getArticles(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.urlAPI + '/api/articles', fetchAuth());
  }

  removeArticle(index: number): Observable<void> {
    const deleteURL = this.urlAPI + '/api/articles/' + index;
    console.log(deleteURL);

    return this.httpClient.delete<void>(deleteURL, fetchAuth());
  }

  addArticle(article: Article): Observable<Article> {
    const addURL = this.urlAPI + '/api/articles';

    const content = {
      libelle: article.nom,
      quantity: article.quantite,
      price: article.prix,
    };

    console.log('sent : ' + content);

    return this.httpClient.post<Article>(addURL, content, fetchAuth());
  }

  getPrixTotal(): number {
    let prixTotal: number = 0;

    for (let article of this.liste) {
      prixTotal += article.prix * article.quantite;
    }

    return prixTotal;
  }
}
