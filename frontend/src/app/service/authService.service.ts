import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private tokenUrl = 'http://localhost:8080';
  clientId = 'myclientid'; // colocar em variavel de ambiente
  clientSecret = 'myclientsecret'; // colocar em variavel de ambiente

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      Authorization: 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
    });

    const body = new HttpParams()
      .set('grant_type', 'password')
      .set('username', username)
      .set('password', password);

    return this.http.post(`${this.tokenUrl}/oauth2/token`, body.toString(), { headers });
  }

  logout(): void {
    localStorage.clear();
  }

  getDecodedToken(): any | null {
    const token = localStorage.getItem('access_token');
    if (!token) return null;

    try {
      return jwtDecode(token);
    } catch (e) {
      console.error('Token inválido', e);
      return null;
    }
  }

  getUserRole(): string {
    const decoded = this.getDecodedToken();
    return decoded?.authorities?.[0];
  }

  isOng(): boolean {
    if (!this.getDecodedToken()) return false;
    return this.getUserRole() === 'ROLE_ONG';
  }

  isVoluntario(): boolean {
    if (!this.getDecodedToken()) return false;
    return this.getUserRole() === 'ROLE_VOLUNTARIO';
  }

  resetPassword(token: string, password: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
    });

    const body = {
      token,
      password,
    };

    return this.http.put(`${this.tokenUrl}/auth/resetar-senha`, body, { headers });
  }
}
