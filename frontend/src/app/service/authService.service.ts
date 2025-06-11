import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private API = 'http://localhost:8080';
  private readonly TOKEN_KEY = 'access_token'; // Nome consistente
  clientId = 'myclientid'; // colocar em variavel de ambiente
  clientSecret = 'myclientsecret'; // colocar em variavel de ambiente

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  getToken(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem(this.TOKEN_KEY);
    }
    return null;
  }

  setToken(token: string): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem(this.TOKEN_KEY, token);
    }
  }

  removeToken(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem(this.TOKEN_KEY);
    }
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

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
    if (isPlatformBrowser(this.platformId)) {
      localStorage.clear();
    }
  }

  getDecodedToken(): any | null {
    const token = this.getToken();
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
}
