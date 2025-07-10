import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HistoricoAtividade } from '../model/historico-atividade.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HistoricoAtividadeService {
  private baseUrl = 'http://localhost:8080/api/v1/atividades';

  constructor(private http: HttpClient) {}

  getAtividadeAtual(): Observable<HistoricoAtividade> {
    return this.http.get<HistoricoAtividade>(`${this.baseUrl}/atual`);
  }

  getAtividadesRealizadas(): Observable<HistoricoAtividade[]> {
    return this.http.get<HistoricoAtividade[]>(`${this.baseUrl}/realizadas`);
  }
}
