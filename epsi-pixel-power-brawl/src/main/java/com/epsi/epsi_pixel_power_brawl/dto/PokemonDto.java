package com.epsi.epsi_pixel_power_brawl.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonDto {
    private int pokedexId;
    private String category;
    private String name;
    private SpritesDto sprites;
    private List<TypesDto> types;
    private List<TalentsDto> talents;
    private StatsDto stats;
    private List<ResistancesDto> resistances;
    private boolean isShiny;
}
