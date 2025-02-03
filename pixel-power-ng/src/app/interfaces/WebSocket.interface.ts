import { PokemonTeam, SimplifiedPokemonTeam } from './Battle.interface';

export interface WebSocketMessage {
  type: string;
  content: any;
}

export interface SendTeamInfo {
  pokemonTeam: SimplifiedPokemonTeam;
  username: string;
  token: string;
}

export interface BattleUserInfo {
  pokemonTeam: SimplifiedPokemonTeam;
  username: string;
}

export interface UpdateMatchmaking {
  updateWaitingUsers: BattleUserInfo[];
  type: string;
}

export interface BeginBattleInfo {
  pokemonTeam: SimplifiedPokemonTeam;
  username: string;
  type: string;
}

export interface PokemonBattleUpdate {
  team1: BattlePokemonTeam;
  team2: BattlePokemonTeam;
  p1Turn: boolean;
  type: string;
}

export interface BattlePokemon {
  pokedexID: number;
  isShiny: boolean;
  HP: number;
  DEF: number;
}

export interface BattlePokemonTeam {
  battlePokemonList: BattlePokemon[];
}