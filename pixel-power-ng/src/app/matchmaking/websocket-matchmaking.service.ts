import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { WebSocketMessage } from '../interfaces/WebSocket.interface';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private socket$: WebSocketSubject<WebSocketMessage> | null = null;

  constructor() {}

  connect(url: string): void {
    if (!this.socket$ || this.socket$.closed) {
      this.socket$ = webSocket<WebSocketMessage>({
        url,
        openObserver: {
          next: () => console.log('WebSocket connection established'),
        },
        closeObserver: {
          next: () => console.log('WebSocket connection closed'),
        },
        // errorObserver: {
        //   next: (err) => console.error('WebSocket connection error:', err),
        // },
      });
    } else {
      console.warn('WebSocket is already connected.');
    }
  }

  sendMessage(message: WebSocketMessage): void {
    if (this.socket$) {
      console.log('Sending message:', message);
      this.socket$.next(message);
    } else {
      console.error('WebSocket is not connected. Call `connect` first.');
    }
  }

  getMessages(): Observable<WebSocketMessage> {
    if (this.socket$) {
      return this.socket$.asObservable();
    } else {
      throw new Error('WebSocket is not connected. Call `connect` first.');
    }
  }

  // Close the connection
  close(): void {
    if (this.socket$) {
      this.socket$.complete();
      this.socket$ = null; // Reset the socket reference after closing
    } else {
      console.warn('WebSocket is not connected or already closed.');
    }
  }
}
