// src/app/service/atividade.service.ts
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import {
  Atividade,
  AtualizarAtividade,
  CarregarDadosAtividade,
} from '../model/atividade.model';
import { catchError } from 'rxjs/operators';
interface Page<T> {
  content: T[];
}
// Define the Atividade interface
export interface ListaAprovados {
  quantidadeVoluntarios: number;
  voluntarios: Array<{
    idVoluntario: number;
    nomeVoluntario: string;
  }>;
}
@Injectable({ providedIn: 'root' })
export class GestaoVoluntarioService {
  private baseUrl = 'http://localhost:8080/api/historico-atividades';

  constructor(private http: HttpClient) {}

  //http://localhost:8080/api/historico-atividades/listar-voluntarios-aprovados/atividade/1
  // isso será usado para listar todos os voluntários inscritos e aprovados na atividade
  getVoluntariosAprovados(idAtividade: number): Observable<ListaAprovados> {
    return this.http
      .get<ListaAprovados>(`${this.baseUrl}/listar-voluntarios-aprovados/atividade/${idAtividade}`)
      .pipe(
        catchError((err: HttpErrorResponse) => {
          console.error('Erro ao buscar voluntarios aprovados para esta atividade', err);
          return throwError(() => err);
        })
      );
  }
}
