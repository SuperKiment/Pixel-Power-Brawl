import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'layout',
  standalone: true,
  imports: [RouterOutlet, MatButtonModule],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css',
})
export class LayoutComponent {
  username: string = '';

  constructor() {
    const stockedName = localStorage.getItem('username');
    if (stockedName) this.username = stockedName;
  }
}
