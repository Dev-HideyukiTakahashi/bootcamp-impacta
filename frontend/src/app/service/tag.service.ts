// src/app/service/tag.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tag } from '../model/tag.model';

@Injectable({ providedIn: 'root' })
export class TagService {
  private baseUrl = 'http://localhost:8080/tags';

  constructor(private http: HttpClient) {}

  getTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>(this.baseUrl);
  }
}
