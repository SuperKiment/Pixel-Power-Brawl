import { Component, OnInit } from '@angular/core';
import { WebSocketService } from './websocket-matchmaking.service';
import { CommonModule } from '@angular/common';
import { TeamChoosingService } from '../team-choosing/team-choosing.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  standalone: true,
  selector: 'app-websocket',
  templateUrl: './matchmaking.component.html',
  imports: [CommonModule, HttpClientModule],
  providers: [],
})
export class MatchmakingComponent implements OnInit {
  public isConnected = false;

  constructor(
    private webSocketService: WebSocketService,
    private teamChoosingService: TeamChoosingService
  ) {
    this.updateIsConnected();
  }

  ngOnInit(): void {
    this.connect();
  }

  connect(): void {
    this.webSocketService
      .connect('ws://192.168.1.17:8081/ws-pokemon-matchmaking')
      .subscribe((event: Event) => {
        this.updateIsConnected();
        this.traiterEvent(event);
      });
  }

  traiterEvent(event: Event): void {
    switch (event.type) {
      case 'open':
        console.log('WebSocket connection established');
        this.sendTeam();
        break;
      case 'message':
        const eventMessage = event as MessageEvent;
        this.traiterMessage(eventMessage);
        break;
      case 'close':
        console.log('WebSocket connection closed');
        break;
      default:
        console.error('Unhandled event type:', event.type);
        break;
    }
  }

  traiterMessage(event: MessageEvent): void {
    console.log('Received message:', event.data);
  }

  sendMessage(): void {
    this.webSocketService.sendMessage('Hello Server!');
    this.updateIsConnected();
  }

  disconnect(): void {
    this.webSocketService.disconnect();
    this.updateIsConnected();
  }

  updateIsConnected(): void {
    this.isConnected = this.webSocketService.isConnected();

    console.log(this.isConnected);
  }

  sendTeam(): void {
    console.log(this.teamChoosingService.getPokemonTeam());
    this.webSocketService.sendMessage(
      JSON.stringify(this.teamChoosingService.getSimplifiedPokemonTeam())
    );
  }
}
