import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private socket!: WebSocket;

  connect(url: string): Observable<Event> {
    return new Observable((observer) => {
      if (!this.isConnected()) {
        this.socket = new WebSocket(url);

        this.socket.onopen = (event) => {
          console.log('WebSocket connection established:', event);
          observer.next(event); // Émet l'événement d'ouverture
        };

        this.socket.onmessage = (event) => {
          console.log('Message received from server:', event.data);
          observer.next(event); // Émet l'événement de message reçu
        };

        this.socket.onerror = (event) => {
          console.error('WebSocket error:', event);
          observer.error(event); // Émet une erreur
        };

        this.socket.onclose = (event) => {
          console.log('WebSocket connection closed:', event);
          observer.complete(); // Termine l'observable lorsque la connexion se ferme
        };
      } else {
        console.log('Déjà connecté !');
        observer.complete(); // Complète l'observable immédiatement
      }
      return () => {
        if (this.socket) {
          this.socket.close();
        }
      };
    });
  }

  sendMessage(message: string): void {
    if (this.isConnected()) {
      this.socket.send(message);
    } else {
      console.error('WebSocket is not open.');
    }
  }

  disconnect(): void {
    if (this.socket) {
      this.socket.close();
    }
  }
  isConnected(): boolean {
    return this.socket && this.socket.readyState === WebSocket.OPEN;
  }
}
