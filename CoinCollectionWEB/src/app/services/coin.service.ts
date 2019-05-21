import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Coin } from '../models/coin.Model';
import { of, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoinService {
  coinsUrl: string = "http://localhost:8080/coincollection/coins"

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Coin[]> {
    return of([
      {
        id: 1,
        value: 10,
        preservation: 10,
        yearOfCoinage: 1990,
        front: undefined,
        back: undefined,
        source: {
          id: 1,
          name: "Kaffeedose"
        },
        currency: {
          id: 1,
          name: "Groschen"
        },
        collector: {
          id: 1,
          firstName: "Franz",
          lastName: "Schwarcz"
        }
      }
    ]);
  }

  getFrontImageUrl(id: number): string {
    return this.coinsUrl + '/' + id + '/front';
  }

  getBackImageUrl(id: number): string {
    return this.coinsUrl + '/' + id + '/back';
  }
}
