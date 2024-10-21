import { TestBed } from '@angular/core/testing';

import { FormulaireServiceService } from './formulaire-service.service';

describe('FormulaireServiceService', () => {
  let service: FormulaireServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormulaireServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
