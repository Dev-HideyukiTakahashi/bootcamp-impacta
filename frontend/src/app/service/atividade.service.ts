// src/app/service/atividade.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Atividade } from '../model/atividade.model';

interface Page<T> {
  content: T[];
}

@Injectable({ providedIn: 'root' })
export class AtividadeService {
  private baseUrl = 'http://localhost:8080/api/atividades';

  constructor(private http: HttpClient) {}

  getAtividades(): Observable<Atividade[]> {
    return this.http.get<Atividade[]>(`${this.baseUrl}/buscar`);
  }

  cadastrarAtividade(payload: any): Observable<Atividade> {
    return this.http.post<Atividade>(this.baseUrl, payload);
  }

  /**
   * Atualiza apenas o campo statusAtividade de uma atividade específica.
   * Ajuste o endpoint conforme sua API (PATCH ou PUT).
   */
  atualizarStatus(id: number, status: string): Observable<Atividade> {
    // Exemplo: PUT http://localhost:8080/api/atividades/{id}/status
    return this.http.put<Atividade>(`${this.baseUrl}/${id}/status`, {
      statusAtividade: status
    });
  }
}
