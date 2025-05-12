import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VoluntarioService {
  private apiUrl = 'http://localhost:8080/api/voluntario/cadastrar';

  constructor(private http: HttpClient) {}

  cadastrarVoluntario(voluntario: any): Observable<any> {
    return this.http.post(this.apiUrl, voluntario);
  }
}
