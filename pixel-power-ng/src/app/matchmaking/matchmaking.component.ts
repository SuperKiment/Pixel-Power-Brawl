import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { WebSocketService } from './websocket-matchmaking.service';
import { WebSocketMessage } from '../interfaces/WebSocket.interface';

@Component({
  selector: 'matchmaking',
  standalone: true,
  imports: [HttpClientModule],
  templateUrl: './matchmaking.component.html',
  providers: [WebSocketService],
  styleUrl: './matchmaking.component.css',
})
export class MatchmakingComponent {
  constructor(
    private http: HttpClient,
    private wsMatchmaking: WebSocketService
  ) {}

  connectToServer() {
    this.wsMatchmaking.connect('ws://192.168.1.17:8081/ws-pokemon-matchmaking');
  }

  sendMessage(message: string="a") {
    const wsMessage: WebSocketMessage = { type: 'MESSAGE', content: "message" };
    this.wsMatchmaking.sendMessage(wsMessage);
  }
}
