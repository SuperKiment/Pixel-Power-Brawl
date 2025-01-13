import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { WebSocketMessage } from '../interfaces/WebSocket.interface';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  constructor(private socket$: WebSocketSubject<WebSocketMessage>) {}

  connect(url: string): void {
    this.socket$ = webSocket({
      url: 'ws://localhost:8081/ws-pokemon-matchmaking',
      openObserver: {
        next: () => {
          console.log('Connection established');
        },
      },
    });
  }

  sendMessage(message: WebSocketMessage): void {
    if (this.socket$) {
      this.socket$.next(message);
    }
  }

  getMessages(): Observable<WebSocketMessage> {
    if (!this.socket$) {
      throw new Error('WebSocket non connecté. Appelez `connect` en premier.');
    }
    return this.socket$.asObservable();
  }

  // Fermeture de la connexion
  close(): void {
    if (this.socket$) {
      this.socket$.complete();
    }
  }
}
