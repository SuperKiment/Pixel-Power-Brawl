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
