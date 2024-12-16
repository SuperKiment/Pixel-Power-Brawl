import { TestBed } from '@angular/core/testing';

import { WebsocketMatchmakingService } from './websocket-matchmaking.service';

describe('WebsocketMatchmakingService', () => {
  let service: WebsocketMatchmakingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WebsocketMatchmakingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
