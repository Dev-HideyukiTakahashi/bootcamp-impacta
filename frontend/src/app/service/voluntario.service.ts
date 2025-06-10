import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { PerfilVoluntario, CarregarDadosVoluntario } from '../model/VoluntarioModel';

@Injectable({
  providedIn: 'root'
})
export class VoluntarioService {
  private baseUrl = 'http://localhost:8080/api/voluntario';

  constructor(private http: HttpClient) { }

  cadastrarVoluntario(voluntario: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/cadastrar`, voluntario)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.error('Erro ao cadastrar voluntário', error);
          return throwError(() => error);
        })
      );
  }

  getMeuPerfil(): Observable<PerfilVoluntario> {
    return this.http.get<PerfilVoluntario>(`${this.baseUrl}/perfil-voluntario`)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.error('Erro ao buscar perfil resumido', error);
          return throwError(() => error);
        })
      );
  }

  DadosVoluntario(): Observable<CarregarDadosVoluntario> {
    return this.http.get<CarregarDadosVoluntario>(`${this.baseUrl}/dados-voluntario`)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.error('Erro ao buscar dados completos do voluntário', error);
          return throwError(() => error);
        })
      );
  }

  atualizarVoluntario(dados: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/editar`, dados)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.error('Erro ao atualizar perfil', error);
          return throwError(() => error);
        })
      );
  }
}
