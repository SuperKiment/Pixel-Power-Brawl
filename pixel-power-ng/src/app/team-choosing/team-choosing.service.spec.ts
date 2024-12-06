import { TestBed } from '@angular/core/testing';

import { TeamChoosingService } from './team-choosing.service';

describe('TeamChoosingService', () => {
  let service: TeamChoosingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeamChoosingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
