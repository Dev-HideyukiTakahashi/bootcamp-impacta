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
export interface HistoricoVoluntarios {
  quantidadeVoluntarios: number;
  voluntarios: Array<{
    idVoluntario: number;
    nomeVoluntario: string;
  }>;
}
@Injectable({ providedIn: 'root' })
export class AtividadeService {
  private baseUrl = 'http://localhost:8080/api/atividades';

  constructor(private http: HttpClient) { }

  getAtividades(): Observable<Atividade[]> {
    return this.http.get<Atividade[]>(`${this.baseUrl}/buscar`);
  }

  cadastrarAtividade(payload: any): Observable<Atividade> {
    return this.http.post<Atividade>(this.baseUrl, payload);
  }

  atualizarStatus(
    id: number,
    status: 'ANDAMENTO' | 'ENCERRADA' | 'CONGELADA'
  ): Observable<Atividade> {
    const url = `${this.baseUrl}/${id}/status/${status}`;
    return this.http.patch<Atividade>(url, null);
  }

  getAtividadePorId(id: number): Observable<Atividade> {
    return this.http.get<Atividade>(`${this.baseUrl}/dados-atividade/${id}`);
  }

  atualizarAtividade(
    id: number,
    atividade: AtualizarAtividade
  ): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/editar/${id}`, atividade);
  }

  DadosAtividade(): Observable<CarregarDadosAtividade> {
    return this.http
      .get<CarregarDadosAtividade>(`${this.baseUrl}/dados-atividade`)
      .pipe(
        catchError((error: HttpErrorResponse) => {

          console.error('Erro ao buscar dados completos da atividade', error);
          return throwError(() => error);
        })
      );
  }
  // Método para buscar o histórico de voluntários de uma atividade
  // Recebe o ID da atividade e retorna um Observable com o histórico de voluntários
  // O histórico inclui a quantidade de voluntários e uma lista com os nomes de cada voluntário
  //api/atividades/1/historico
  getHistorico(idAtividade: number): Observable<HistoricoVoluntarios> {
    return this.http
      .get<HistoricoVoluntarios>(`${this.baseUrl}/${idAtividade}/historico`)
      .pipe(
        catchError((err: HttpErrorResponse) => {
          console.error('Erro ao buscar histórico', err);
          return throwError(() => err);
        })
      );
  }
  getBuscarAtividade(): Observable<{ content: Atividade[] }> {
    return this.http.get<{ content: Atividade[] }>(this.baseUrl);
  }
  atualizarCandidatura(atividadeId: number): Observable<any> {
    return this.http.post('http://localhost:8080/api/atividades/atualizar-candidatura', { atividadeId });
  }
}
