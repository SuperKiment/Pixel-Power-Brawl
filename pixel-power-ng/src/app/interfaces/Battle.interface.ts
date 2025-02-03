import { Move, Pokemon, SimplifiedPokemon } from './Pokemon.interface';

export enum Status {
  POISON = 'Empoisonné',
  SOMMEIL = 'Endormi',
  PARALISIE = 'Paralisé',
  BRULURE = 'Brûlé',
  GEL = 'Gelé',
  NO_STATUS = 'Pas de status',
}

export interface BattlePokemon {
  pokemon: Pokemon;
  moves: Move[];
  status: Status;
  isConfused: boolean;
  turnCount: number;
}

export interface PokemonTeam {
  pokemons: (Pokemon | null)[];
}

export interface SimplifiedPokemonTeam {
  pokemons: (SimplifiedPokemon | null)[];
}

export enum GameStateEnum {
  MY_TURN,
  OTHERS_TURN,
  MY_WIN,
  OTHERS_WIN,
}

export interface GameState {
  turnCount: number;
  battleState: GameStateEnum;
}
