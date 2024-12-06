import { Move, Pokemon } from './Pokemon.interface';

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
  pokemons: Pokemon[];
}

export enum GameStateEnum {
  CHOOSING_MOVE,
}

export interface GameState {
  turnCount: number;
  state: GameStateEnum;
}
