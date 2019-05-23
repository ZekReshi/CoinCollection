import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Source } from '../models/source.Model';

@Injectable({
  providedIn: 'root'
})
export class SourceService {
  sourcesUrl: string = "http://localhost:8080/coincollection/sources"

  constructor(private http: HttpClient) { }

  getAll(): Observable<Source[]> {
    return of([
      {
        id: 1,
        name: "Kaffeedose"
      },
      {
        id: 2,
        name: "Mappe"
      }
    ]);
  }
}
