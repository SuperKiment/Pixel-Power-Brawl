import { Component, Input } from '@angular/core';
import { Pokemon } from '../interfaces/Pokemon.interface';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'detail-pokemon',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './detail-pokemon.component.html',
  styleUrl: './detail-pokemon.component.css',
})
export class DetailPokemonComponent {
  @Input() selectedPokemon: Pokemon | null = null;
}
