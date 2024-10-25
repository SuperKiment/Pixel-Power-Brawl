import { TestBed } from '@angular/core/testing';

import { ServiceKimentService } from './service-kiment.service';

describe('ServiceKimentService', () => {
  let service: ServiceKimentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceKimentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
