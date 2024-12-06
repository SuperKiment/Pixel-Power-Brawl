import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TeamChoosingService {
  private urlPokemonAPI = 'https://tyradex.vercel.app/api/v1/pokemon';

  constructor(private httpClient: HttpClient) {}

  public getAllPokemons(): Observable<any> {
    return this.httpClient.get<any>(this.urlPokemonAPI);
  }
}
