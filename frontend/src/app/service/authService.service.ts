import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private API = 'http://localhost:8080';
  clientId = 'myclientid'; // colocar em variavel de ambiente
  clientSecret = 'myclientsecret'; // colocar em variavel de ambiente

  constructor(private http: HttpClient) {}

  // LOGIN
  login(username: string, password: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      Authorization: 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
    });

    const body = new HttpParams()
      .set('grant_type', 'password')
      .set('username', username)
      .set('password', password);

    return this.http.post(`${this.API}/oauth2/token`, body.toString(), {
      headers,
    });
  }

  // EMAIL COM TOKEN PARA RECUPERAR SENHA
  recuperarSenha(email: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
    });

    return this.http.post(
      `${this.API}/auth/recuperar-senha`,
      { email },
      { headers }
    );
  }

  // RESET DE SENHA
  resetPassword(token: string, password: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
    });

    const body = {
      token,
      password,
    };

    return this.http.put(`${this.API}/auth/resetar-senha`, body, { headers });
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

  isLoggedIn(): boolean {
    const token = localStorage.getItem('access_token');
    if (!token) return false;

    try {
      const decoded: any = jwtDecode(token);
      const currentTime = Math.floor(Date.now() / 1000);
      return decoded.exp && decoded.exp > currentTime;
    } catch (e) {
      return false;
    }
  }
}
