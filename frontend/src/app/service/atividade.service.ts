// src/app/service/atividade.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Atividade } from '../model/atividade.model';

interface Page<T> {
  content: T[];
  // (podemos omitir o resto das props se não for usar)
}

@Injectable({ providedIn: 'root' })
export class AtividadeService {
  private apiUrl = 'http://localhost:8080/api/atividades';

  constructor(private http: HttpClient) {}

  getAtividades(): Observable<Atividade[]> {
    return this.http
      .get<Page<Atividade>>(this.apiUrl)
      .pipe(
        map(page => page.content)   // pega apenas o array
      );
  }

   /** cadastra uma nova atividade */
  cadastrarAtividade(payload: {
    nome: string;
    periodo: string;
    cargaHorariaDiaria: string;
    endereco: string;
    possuiCertificacao: boolean;
    descricao: string;
  }): Observable<Atividade> {
    return this.http.post<Atividade>(this.apiUrl, payload);
  }
}
