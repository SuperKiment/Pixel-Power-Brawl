import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PokemonTeam } from '../interfaces/Battle.interface';
import fetchAuth from '../utils/fetch-auth';

@Injectable({
  providedIn: 'root',
})
export class TeamChoosingService {
  private urlPokemonAPI = 'https://tyradex.vercel.app/api/v1/pokemon';
  pokemonTeam: PokemonTeam | null = null;

  constructor(private httpClient: HttpClient) {}

  public fetchAllPokemons(): Observable<any> {
    return this.httpClient.get(this.urlPokemonAPI, fetchAuth());
  }

  setPokemonTeam(team: PokemonTeam) {
    this.pokemonTeam = team;
  }
}
