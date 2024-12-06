import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailPokemonComponent } from './detail-pokemon.component';

describe('DetailPokemonComponent', () => {
  let component: DetailPokemonComponent;
  let fixture: ComponentFixture<DetailPokemonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailPokemonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailPokemonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
