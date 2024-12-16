import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  constructor(private socket$: WebSocketSubject<any>) {}

  connect(url: string): void {
    this.socket$ = webSocket(url);
  }

  // Envoi d'un message via le WebSocket
  sendMessage(message: any): void {
    if (this.socket$) {
      this.socket$.next(message);
    }
  }

  // Réception des messages
  getMessages(): Observable<any> {
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
