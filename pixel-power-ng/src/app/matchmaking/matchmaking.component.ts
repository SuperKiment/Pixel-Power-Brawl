import { Component, OnInit } from '@angular/core';
import { WebSocketService } from './websocket-matchmaking.service';
import { CommonModule } from '@angular/common';
import { TeamChoosingService } from '../team-choosing/team-choosing.service';
import { HttpClientModule } from '@angular/common/http';
import { isUserConnected } from '../login-register/login-register.component';
import { Router } from '@angular/router';
import {
  BattlePokemonTeam,
  BattleUserInfo,
  BeginBattleInfo,
  PokemonBattleUpdate,
  SendTeamInfo,
  UpdateMatchmaking,
} from '../interfaces/WebSocket.interface';

@Component({
  standalone: true,
  selector: 'app-websocket',
  templateUrl: './matchmaking.component.html',
  imports: [CommonModule, HttpClientModule],
  providers: [],
})
export class MatchmakingComponent implements OnInit {
  public isConnected = false;
  public waitingUsers: BattleUserInfo[] = [];
  public isBattleOn = false;
  public battleOpponent: BattleUserInfo | null = null;
  public amPlayer1 = false;
  public isPlayer1Turn = false;
  public isItMyTurn = false;

  public battleTeam: BattlePokemonTeam | null = null;
  public opponentBattleTeam: BattlePokemonTeam | null = null;

  constructor(
    private webSocketService: WebSocketService,
    private teamChoosingService: TeamChoosingService,
    private router: Router
  ) {
    this.updateIsConnected();
    if (!isUserConnected()) router.navigate(['/login']);
  }

  ngOnInit(): void {
    if (isUserConnected()) this.connect();
  }

  connect(): void {
    this.webSocketService
      .connect('ws://localhost:8081/ws-pokemon-matchmaking')
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
    const data = JSON.parse(event.data);

    switch (data.type) {
      case 'UpdateMatchmaking':
        console.log('UpdateMatchmaking received');
        this.TraiterUpdateMatchmaking(data as UpdateMatchmaking);
        break;
      case 'BattleBegin':
        this.TraiterBeginBattle(data as BeginBattleInfo);
        break;

      case 'PokemonBattleUpdate':
        this.TraiterPokemonBattleUpdate(data as PokemonBattleUpdate);
        break;
    }
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
  }

  sendTeam(): void {
    if (!isUserConnected()) {
      this.router.navigate(['/login']);
      return;
    }

    if (this.teamChoosingService.getPokemonTeam() == null) {
      this.router.navigate(['/team-choosing']);
      return;
    }

    const message: SendTeamInfo = {
      pokemonTeam:
        this.teamChoosingService.getSimplifiedPokemonTeam() ||
        this.teamChoosingService.getSimplifiedDefaultPokemonTeam(),
      username: localStorage.getItem('username') as string,
      token: localStorage.getItem('token') as string,
    };

    console.log(message);

    this.webSocketService.sendMessage(
      JSON.stringify({ ...message, type: 'sendTeamInfo' })
    );
  }

  TraiterUpdateMatchmaking(data: UpdateMatchmaking) {
    console.log(data);
    this.waitingUsers = data.updateWaitingUsers;
  }

  OnWaitingUserSelected(user: BattleUserInfo) {
    console.log('Selected user:', user);
    this.amPlayer1 = true;
    this.webSocketService.sendMessage(
      JSON.stringify({
        username: user.username,
        type: 'WaitingUserSelected',
      })
    );
  }

  TraiterBeginBattle(data: BeginBattleInfo) {
    if (!data.pokemonTeam || !data.username) return;

    this.isBattleOn = true;
    this.battleOpponent = {
      pokemonTeam: data.pokemonTeam,
      username: data.username,
    };

    console.log('Battle started:', data);
  }

  OnAttaque() {
    this.webSocketService.sendMessage(
      JSON.stringify({
        action: 'attack',
        type: 'BattleAction',
      })
    );
  }

  OnDefense() {
    this.webSocketService.sendMessage(
      JSON.stringify({
        action: 'defense',
        type: 'BattleAction',
      })
    );
  }

  OnSoin() {
    this.webSocketService.sendMessage(
      JSON.stringify({
        action: 'heal',
        type: 'BattleAction',
      })
    );
  }

  isMyTurn() {
    return (this.amPlayer1 && this.isPlayer1Turn) || (!this.amPlayer1 && !this.isPlayer1Turn);
  }

  TraiterPokemonBattleUpdate(data: PokemonBattleUpdate) {
    console.log(data);
    this.isItMyTurn = this.isMyTurn();

    this.isPlayer1Turn = data.p1Turn
  }
}
