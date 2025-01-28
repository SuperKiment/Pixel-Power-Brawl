import { Component, OnInit } from '@angular/core';
import { WebSocketService } from './websocket-matchmaking.service';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-websocket',
  templateUrl: './matchmaking.component.html',
  imports: [CommonModule],
})
export class MatchmakingComponent implements OnInit {
  public isConnected = false;

  constructor(private webSocketService: WebSocketService) {
    this.updateIsConnected();
  }

  ngOnInit(): void {}

  connect(): void {
    this.webSocketService
      .connect('ws://192.168.1.17:8081/ws-pokemon-matchmaking')
      .subscribe((event: Event) => {
        this.updateIsConnected();
      });
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
}
