import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { isUserConnected } from '../login-register/login-register.component';

@Component({
  selector: 'layout',
  standalone: true,
  imports: [RouterOutlet, MatButtonModule, HttpClientModule, CommonModule],
  providers: [],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css',
})
export class LayoutComponent {
  username: string = '';

  constructor() {
    const stockedName = localStorage.getItem('username');
    if (stockedName) this.username = stockedName;
  }

  isUserConnected(): boolean {
    return isUserConnected();
  }
}
