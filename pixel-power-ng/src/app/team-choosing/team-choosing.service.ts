import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  PokemonTeam,
  SimplifiedPokemonTeam,
} from '../interfaces/Battle.interface';
import fetchAuth from '../utils/fetch-auth';

@Injectable({
  providedIn: 'root',
})
export class TeamChoosingService {
  private urlPokemonAPI = 'https://tyradex.vercel.app/api/v1/pokemon';
  private pokemonTeam: PokemonTeam | null = null;

  constructor(private httpClient: HttpClient) {}

  public fetchAllPokemons(): Observable<any> {
    return this.httpClient.get(this.urlPokemonAPI, fetchAuth());
  }

  setPokemonTeam(team: PokemonTeam) {
    this.pokemonTeam = team;
  }

  getPokemonTeam(): PokemonTeam | null {
    return this.pokemonTeam;
  }

  getSimplifiedPokemonTeam(): SimplifiedPokemonTeam | null {
    if (this.pokemonTeam) {
      return {
        pokemons: this.pokemonTeam.pokemons.map((member) => ({
          pokedexID: member?.pokedexID || 1,
          isShiny: member?.isShiny || false,
        })),
      };
    }
    return null;
  }
}
