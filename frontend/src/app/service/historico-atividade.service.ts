import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HistoricoAtividade } from '../model/historico-atividade.model';

@Injectable({
  providedIn: 'root',
})
export class HistoricoAtividadeService {
  private baseUrl = 'http://localhost:8080/api/historico-atividades';

  constructor(private http: HttpClient) {}

  getAtividadesRealizadas(): Observable<HistoricoAtividade[]> {
    return this.http.get<HistoricoAtividade[]>(`${this.baseUrl}`);
  }
}
