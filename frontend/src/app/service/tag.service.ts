import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
// Interface para representar os dados da tag
export interface TagRequestDTO {
  id?: number;
  nome: string;
  // outros campos que sua tag possa ter
}

@Injectable({
  providedIn: 'root',
})
export class TagService {
  private apiUrl = 'http://localhost:8080/api/tags';

  constructor(private http: HttpClient) {}

  buscarTodos(): Observable<any[]> {
    let headers = new HttpHeaders();

    // Verificar se está rodando no navegador antes de acessar localStorage
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('userToken');
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
    }

    return this.http.get<TagRequestDTO[]>(this.apiUrl, { headers });
  }
  platformId(platformId: any) {
    throw new Error('Method not implemented.');
  }

  buscarPorId(id: number): Observable<TagRequestDTO> {
    const token = localStorage.getItem('userToken');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    return this.http.get<TagRequestDTO>(`${this.apiUrl}/${id}`, { headers });
  }

  atualizarTags(ids: number[]) {
    return this.http.put(`${this.apiUrl}`, ids);
  }
}
