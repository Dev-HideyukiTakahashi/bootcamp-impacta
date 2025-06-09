import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OngService {
  private apiUrl = 'http://localhost:8080/api/ong/cadastrar';

  constructor(private http: HttpClient) {}

  cadastrarOng(ong: any): Observable<any> {
    return this.http.post(this.apiUrl, ong);
  }
}
