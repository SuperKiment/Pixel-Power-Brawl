import { TestBed } from '@angular/core/testing';

import { DetailPokemonService } from './detail-pokemon.service';

describe('DetailPokemonService', () => {
  let service: DetailPokemonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetailPokemonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
