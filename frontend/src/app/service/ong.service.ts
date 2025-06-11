import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AtualizarOng, CarregarDadosOng, PerfilOng } from '../model/OngModel';

@Injectable({
  providedIn: 'root',
})
export class OngService {
  private baseUrl = 'http://localhost:8080/api/ong';

  constructor(private http: HttpClient) {}

  cadastrarOng(ong: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/cadastrar`, ong).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Erro ao cadastrar ONG', error);
        return throwError(() => error);
      })
    );
  }

  getMeuPerfil(): Observable<PerfilOng> {
    return this.http.get<PerfilOng>(`${this.baseUrl}/perfil-ong`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Erro ao buscar perfil resumido ONG', error);
        return throwError(() => error);
      })
    );
  }

  DadosOng(): Observable<CarregarDadosOng> {
    return this.http.get<CarregarDadosOng>(`${this.baseUrl}/dados-ong`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Erro ao buscar dados completos da ONG', error);
        return throwError(() => error);
      })
    );
  }

  atualizarOng(dados: AtualizarOng): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/editar`, dados).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Erro ao atualizar ONG', error);
        return throwError(() => error);
      })
    );
  }
}
