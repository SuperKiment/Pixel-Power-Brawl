import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  PokemonTeam,
  SimplifiedPokemonTeam,
} from '../interfaces/Battle.interface';
import fetchAuth from '../utils/fetch-auth';
import { API_URL } from '../environment';
import { isUserConnected } from '../login-register/login-register.component';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class TeamChoosingService {
  private urlPokemonAPI = "http://" + API_URL + 'pokemons';
  private pokemonTeam: PokemonTeam | null = null;

  constructor(private httpClient: HttpClient, private router: Router) {
    if (!isUserConnected()) {
      router.navigate(['/login']);
    }
  }

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

  getSimplifiedDefaultPokemonTeam(): SimplifiedPokemonTeam {
    return {
      pokemons: Array(6).fill({
        pokedexID: 1,
        isShiny: true,
      }),
    };
  }
}
