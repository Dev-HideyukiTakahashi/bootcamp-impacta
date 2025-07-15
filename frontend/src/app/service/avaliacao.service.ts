import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AvaliacaoRequest, AvaliacaoResponse } from '../model/avaliacao.model';

@Injectable({ providedIn: 'root' })
export class AvaliacaoService {
  // pontua aqui pro endpoint de avaliações
  private readonly baseUrl = 'http://localhost:8080/api/avaliacoes';

  constructor(private http: HttpClient) {}

  // consome POST /api/avaliacoes
  avaliarVoluntario(request: AvaliacaoRequest): Observable<AvaliacaoResponse> {
    return this.http.post<AvaliacaoResponse>(this.baseUrl, request);
  }
  resumoAvaliacao(historicoId: number): Observable<AvaliacaoResponse> {
    return this.http.get<AvaliacaoResponse>(
      `${this.baseUrl}/historicoId/${historicoId}`
    );
  }
}
