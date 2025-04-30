import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private tokenUrl = 'http://localhost:8080/oauth2/token';

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    const clientId = 'myclientid';
    const clientSecret = 'myclientsecret';

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      Authorization: 'Basic ' + btoa(`${clientId}:${clientSecret}`),
    });

    const body = new HttpParams()
      .set('grant_type', 'password')
      .set('username', username)
      .set('password', password);

    return this.http.post(this.tokenUrl, body.toString(), { headers });
  }
}
