/**
 * Pokemon resistance
 */
export interface Resistance {
  multiplier: number;
  name: string;
}

/**
 * Pokemon Type
 */
export interface Type {
  name: string;
  image: string;
}

/**
 * Pokemon talent
 */
export interface Talent {
  name: string;
  tc: boolean;
}

/**
 * Pokemon Stats
 */
export interface Stats {
  hp: number;
  atk: number;
  def: number;
  spe_atk: number;
  spe_def: number;
  vit: number;
}

/**
 * Pokemon sprites
 */
export interface Sprites {
  regular: string;
  shiny: string;
  gmax: string | null;
}

/**
 * Pokemon move
 */
export interface Move {
  name: string;
  accuracy: number;
  power: number;
  pp: number;
}

/**
 * Base Pokemon interface
 */
export interface Pokemon {
  category: string;
  name: string;
  pokedexID: number;
  resistances: Resistance[];
  sprites: Sprites;
  types: Type[];
  talents: Talent[];
  stats: Stats;
  isShiny: boolean;
}

/**
 * Simplified Pokemon interface
 */
export interface SimplifiedPokemon {
  pokedexID: number;
  isShiny: boolean;
}
