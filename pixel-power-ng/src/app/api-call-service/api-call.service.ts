import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_URL } from '../environment';

interface LoginBody {
  username: string;
  password: string;
}

interface RegisterBody {
  username: string;
  password: string;
  matchingPassword: string;
}

@Injectable({
  providedIn: 'root',
})
export class ApiCallService {
  constructor(private httpClient: HttpClient) {}

  public login(body: LoginBody): Observable<HttpResponse<Object>> {
    return this.httpClient.post(API_URL + 'user/login', body, {
      observe: 'response',
    });
  }

  public register(body: RegisterBody): Observable<string> {
    return this.httpClient.post(API_URL + 'user/registration', body, {
      responseType: 'text',
    });
  }
}
