import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { HistoricoAtividade } from '../model/historico-atividade.model';

interface PaginaHistorico {
  content: HistoricoAtividade[];
  totalPages: number;
  totalElements: number;
  number: number;
  size: number;
}

@Injectable({
  providedIn: 'root',
})
export class HistoricoAtividadeService {
  private baseUrl = 'http://localhost:8080/api/historico-atividades';

  constructor(private http: HttpClient) {}

  getAtividadesRealizadas(page: number = 0): Observable<HistoricoAtividade[]> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<PaginaHistorico>(this.baseUrl, { params }).pipe(map((res) => res.content));
  }
}
