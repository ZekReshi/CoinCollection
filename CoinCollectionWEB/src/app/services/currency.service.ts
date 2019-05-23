import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Currency } from '../models/currency.Model';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  currenciesUrl: string = "http://localhost:8080/coincollection/currencies"

  constructor(private http: HttpClient) { }

  getAll(): Observable<Currency[]> {
    return of([
      {
        id: 1,
        name: "Groschen"
      },
      {
        id: 2,
        name: "Schilling"
      },
      {
        id: 3,
        name: "Franken"
      },
      {
        id: 4,
        name: "Kronen"
      }
    ]);
  }
}
