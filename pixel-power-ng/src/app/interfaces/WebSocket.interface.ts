import { PokemonTeam, SimplifiedPokemonTeam } from './Battle.interface';

export interface WebSocketMessage {
  type: string;
  content: any;
}

export interface SendTeamInfo {
  pokemonTeam: SimplifiedPokemonTeam;
  username: string;
  auth_token: string;
}
