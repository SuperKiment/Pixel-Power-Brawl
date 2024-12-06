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

  getMultiplierColor(multiplier: number): string {
    switch (multiplier) {
      case 0:
        return 'gray';
      case 0.25:
        return 'very-green';
      case 0.5:
        return 'green';
      case 1:
        return '';
      case 2:
        return 'red';
      case 4:
        return 'very-red';
    }

    return '';
  }
}
