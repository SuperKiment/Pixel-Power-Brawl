import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import fetchAuth from '../utils/fetch-auth';
import { WebSocketService } from './websocket-matchmaking.service';
import { WebSocketSubject } from 'rxjs/webSocket';

@Component({
  selector: 'matchmaking',
  standalone: true,
  imports: [HttpClientModule],
  templateUrl: './matchmaking.component.html',
  styleUrl: './matchmaking.component.css',
})
export class MatchmakingComponent {
  constructor(
    private http: HttpClient,
    private wsMatchmaking: WebSocketService
  ) {}

  getData() {
    this.http
      .get('http://localhost:8081/pokemon', fetchAuth())
      .subscribe((data) => {
        console.log(data);
      });
  }

  connectToServer() {
    this.wsMatchmaking.connect('ws://localhost:8081/ws-pokemon-matchmaking');
  }
}
