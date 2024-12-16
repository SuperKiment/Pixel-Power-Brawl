import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'profil',
  standalone: true,
  imports: [],
  templateUrl: './profil.component.html',
  styleUrl: './profil.component.css',
})
export class ProfilComponent {
  username: string = '';

  constructor(private router: Router) {
    const storedUsername = localStorage.getItem('username');
    if (storedUsername) this.username = storedUsername;
    else router.navigate(['/login']);
  }

  onSeDeconnecter() {
    console.log('déconnection');
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    this.router.navigate(['/login']);
  }
}
