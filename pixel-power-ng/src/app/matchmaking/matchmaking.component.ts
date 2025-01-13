import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { JwtInterceptor } from '../interceptors/jwt.interceptor';
import fetchAuth from '../utils/fetch-auth';

@Component({
  selector: 'matchmaking',
  standalone: true,
  imports: [HttpClientModule],
  providers: [],
  templateUrl: './matchmaking.component.html',
  styleUrl: './matchmaking.component.css',
})
export class MatchmakingComponent {
  constructor(private http: HttpClient) {}

  getData() {
    this.http
      .get('http://localhost:8081/pokemon', fetchAuth())
      .subscribe((data) => {
        console.log(data);
      });
  }
}
