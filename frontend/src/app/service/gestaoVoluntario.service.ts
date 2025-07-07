// src/app/service/atividade.service.ts
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import {
  Atividade,
  AtualizarAtividade,
  CarregarDadosAtividade,
} from '../model/atividade.model';

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
export interface ListaInscritos {
  id: number;
  nomeCompleto: string;
  cidade: string;
  statusCandidatura: string;
  tags: string[];
}
export type StatusCandidatura = 'PENDENTE' | 'APROVADO' | 'REJEITADO' | 'REALIZADO';

@Injectable({ providedIn: 'root' })
export class GestaoVoluntarioService {
  private baseUrl = 'http://localhost:8080/api/historico-atividades';

  constructor(private http: HttpClient) {}

  // Lista todos os voluntários aprovados na atividade
  getVoluntariosAprovados(idAtividade: number): Observable<ListaAprovados> {
    return this.http
      .get<ListaAprovados>(
        `${this.baseUrl}/listar-voluntarios-aprovados/atividade/${idAtividade}`
      )
      .pipe(
        catchError((err: HttpErrorResponse) => {
          console.error(
            'Erro ao buscar voluntarios aprovados para esta atividade',
            err
          );
          return throwError(() => err);
        })
      );
  }

  /**
   * Busca todos os voluntários inscritos na atividade informada
   * Agora retorna um array de ListaInscritos
   */
  carregarVoluntarios(idAtividade: number): Observable<ListaInscritos[]> {
    const url = `${this.baseUrl}/gestao-voluntarios/atividade/${idAtividade}`;
    return (
      this.http
        // note que o retorno do back é { quantidade: number; voluntarios: ListaInscritos[] }
        .get<{ quantidade: number; voluntarios: ListaInscritos[] }>(url)
        .pipe(
          map((resp) => resp.voluntarios), // pega apenas o array
          catchError((err) => {
            console.error('Erro ao buscar voluntários inscritos', err);
            return throwError(() => err);
          })
        )
    );
  }

  /**
   * Chama o PUT em /gestao-voluntarios/atividade/{atividadeId}/statusCandidatura/{status}
   * envia no body { voluntarioId }
   */
  atualizarStatusCandidatura(
    atividadeId: number,
    voluntarioId: number,
    status: StatusCandidatura
  ): Observable<void> {
    const url = `${this.baseUrl}/gestao-voluntarios/atividade/${atividadeId}/statusCandidatura/${status}`;
    return this.http.put<void>(url, { voluntarioId });
  }
}
