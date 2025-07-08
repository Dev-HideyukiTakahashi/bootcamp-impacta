// src/app/service/atividade.service.ts
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {
  Atividade,
  AtualizarAtividade,
  CarregarDadosAtividade,
  IAtividade,
} from '../model/atividade.model';
import { HistoricoAtividadeDetalhado } from '../model/HistoricoAtividade.model';
interface Page<T> {
  content: T[];
}
// Define the Atividade interface

@Injectable({ providedIn: 'root' })
export class AtividadeService {
  private baseUrl = 'http://localhost:8080/api/atividades';
  private historyBase = 'http://localhost:8080/api/historico-atividades';
  constructor(private http: HttpClient) {}

  getAtividades(): Observable<Atividade[]> {
    return this.http.get<Atividade[]>(`${this.baseUrl}/buscar`);
  }

  cadastrarAtividade(payload: any): Observable<Atividade> {
    return this.http.post<Atividade>(this.baseUrl, payload);
  }

  atualizarStatus(
    id: number,
    status: 'ANDAMENTO' | 'ENCERRADA' | 'CONGELADA',
  ): Observable<Atividade> {
    const url = `${this.baseUrl}/${id}/status/${status}`;
    return this.http.patch<Atividade>(url, null);
  }

  getAtividadePorId(id: number): Observable<Atividade> {
    return this.http.get<Atividade>(`${this.baseUrl}/dados-atividade/${id}`);
  }

  atualizarAtividade(id: number, atividade: AtualizarAtividade): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/editar/${id}`, atividade);
  }

  DadosAtividade(): Observable<CarregarDadosAtividade> {
    return this.http.get<CarregarDadosAtividade>(`${this.baseUrl}/dados-atividade`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Erro ao buscar dados completos da atividade', error);
        return throwError(() => error);
      }),
    );
  }

  getBuscarAtividade(): Observable<{ content: IAtividade[] }> {
    return this.http.get<{ content: IAtividade[] }>(this.baseUrl);
  }

  atualizarCandidatura(atividadeId: number): Observable<any> {
    return this.http.post('http://localhost:8080/api/atividades/atualizar-candidatura', {
      atividadeId,
    });
  }

  listarTodosVoluntarios(id: number): Observable<HistoricoAtividadeDetalhado> {
    const url = `${this.historyBase}/gestao-voluntarios/atividade/${id}`;
    return this.http.get<HistoricoAtividadeDetalhado>(url).pipe(
      catchError((err: HttpErrorResponse) => {
        console.error('Erro ao buscar voluntários (atividade)', err);
        return throwError(() => err);
      }),
    );
  }

  buscarAtividadesPorTag(tag:string): Observable<{ content: IAtividade[] }> {
    return this.http.get<{ content: IAtividade[] }>(`${this.baseUrl}/tag/`+ tag);
  }
}
