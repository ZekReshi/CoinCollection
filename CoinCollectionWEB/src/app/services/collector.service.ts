import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Collector } from '../models/collector.Model';

@Injectable({
  providedIn: 'root'
})
export class CollectorService {
  collectorsUrl: string = "http://localhost:8080/coincollection/collectors"

  constructor(private http: HttpClient) { }

  getAll(): Observable<Collector[]> {
    return of([
      {
        id: 1,
        firstName: "Franz",
        lastName: "Schwarcz"
      },
      {
        id: 2,
        firstName: "Florian",
        lastName: "Schwarcz"
      }
    ]);
  }
}
