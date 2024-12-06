import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { TeamChoosingService } from './team-choosing.service';
import { Pokemon, Type } from '../interfaces/Pokemon.interface';
import {
  FormsModule,
  // NgForm,
  ReactiveFormsModule,
  FormGroup,
  FormBuilder,
} from '@angular/forms';
import { DetailPokemonComponent } from '../detail-pokemon/detail-pokemon.component';

@Component({
  selector: 'app-team-choosing',
  standalone: true,
  imports: [
    HttpClientModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    DetailPokemonComponent,
  ],
  providers: [TeamChoosingService],
  templateUrl: './team-choosing.component.html',
  styleUrl: './team-choosing.component.css',
})
export class TeamChoosingComponent {
  allPokemons: Pokemon[] = [];

  selectForm: FormGroup;
  selectedPokemon: Pokemon | null = null;

  constructor(
    private teamChoosingService: TeamChoosingService,
    fb: FormBuilder
  ) {
    this.selectForm = fb.group({
      memberSelected0: [
        Math.round(Math.random() * this.allPokemons.length) || null,
      ],
      memberSelected1: [
        Math.round(Math.random() * this.allPokemons.length) || null,
      ],
      memberSelected2: [
        Math.round(Math.random() * this.allPokemons.length) || null,
      ],
      memberSelected3: [
        Math.round(Math.random() * this.allPokemons.length) || null,
      ],
      memberSelected4: [
        Math.round(Math.random() * this.allPokemons.length) || null,
      ],
      memberSelected5: [
        Math.round(Math.random() * this.allPokemons.length) || null,
      ],
    });
  }

  ngOnInit() {
    this.updateListe();
  }

  updateListe() {
    this.allPokemons = [];
    this.teamChoosingService.getAllPokemons().subscribe((data: any) => {
      for (let pokemon of data) {
        this.allPokemons.push({
          category: pokemon.category,
          name: pokemon.name.fr,
          pokedexID: pokemon.pokedex_id,
          resistances: pokemon.resistances,
          sprites: pokemon.sprites,
          stats: pokemon.stats,
          talents: pokemon.talents,
          types: pokemon.types,
          isShiny: Math.random() <= 0.01,
        });
      }

      for (let i = 0; i < 6; i++) {
        this.selectForm
          .get('memberSelected' + i)
          ?.setValue(Math.round(Math.random() * this.allPokemons.length));
      }
    });
  }

  onSubmitTeam() {
    console.log(this.selectForm.value);
  }

  getPokemonMember(memberIndex: number): Pokemon | null {
    return this.allPokemons[
      this.selectForm.get('memberSelected' + memberIndex)?.value
    ];
  }

  getPokemonMemberSprite(memberIndex: number): string {
    const pokemon: Pokemon | null = this.getPokemonMember(memberIndex);

    return (
      (pokemon?.isShiny ? pokemon?.sprites.shiny : pokemon?.sprites.regular) ||
      'https://retchhh.wordpress.com/wp-content/uploads/2015/03/loading1.gif'
    );
  }

  getPokemonMemberTypes(memberIndex: number): (string | undefined)[] {
    const types = this.getPokemonMember(memberIndex)?.types || [null, null];
    const sprites = [];

    sprites.push(types[0]?.image);
    sprites.push(types[1]?.image);

    return sprites;
  }

  spriteClicked(memberIndex: number): void {
    this.selectedPokemon =
      this.allPokemons[
        this.selectForm.get('memberSelected' + memberIndex)?.value
      ];
  }
}
