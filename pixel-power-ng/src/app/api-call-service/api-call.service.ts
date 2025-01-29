import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
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

  public login(body: LoginBody): Observable<{ token: string }> {
    return this.httpClient
      .post<{ token: string }>(API_URL + 'user/login', body, {
        responseType: 'json',
      })
      .pipe(catchError(this.handleError));
  }

  public register(body: RegisterBody): Observable<{ response: string }> {
    return this.httpClient
      .post<{ response: string }>(API_URL + 'user/registration', body, {
        responseType: 'json',
      })
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    return throwError(() => new Error(error.error.error || "Erreur inconnue !"));
  }
}
