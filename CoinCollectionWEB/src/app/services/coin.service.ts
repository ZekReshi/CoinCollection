import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Coin } from '../models/coin.Model';
import { of, Observable } from 'rxjs';

const coinHttpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
const imageHttpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'image/*'
  })
}

@Injectable({
  providedIn: 'root'
})
export class CoinService {
  coinsUrl: string = "http://localhost:8080/coincollection/coins"
  coins: Coin[]

  constructor(private http: HttpClient) {
    this.coins = [
      {
        id: 1,
        value: 10,
        preservation: 10,
        yearOfCoinage: 1990,
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
    ];
  }

  getAll(): Observable<Coin[]> {
    //return this.http.get<Coin[]>(this.coinsUrl);
    return of(this.coins);
  }

  postCoin(coin: Coin): Observable<Coin> {
    //return this.http.post<Coin>(this.coinsUrl, coin, coinHttpOptions);
    this.coins = this.coins.concat(coin);
    coin.id = this.coins.length;
    return of(coin);
  }

  postFront(coin: Coin, image: File): Observable<File> {
    //return this.http.post<File>(this.getFrontImageUrl(coin), image, imageHttpOptions);
    return of(null);
  }

  postBack(coin: Coin, image: File): Observable<File> {
    //return this.http.post<File>(this.getBackImageUrl(coin), image, imageHttpOptions);
    return of(null);
  }

  getFrontImageUrl(coin: Coin): string {
    return this.coinsUrl + '/' + coin.id + '/front';
  }

  getBackImageUrl(coin: Coin): string {
    return this.coinsUrl + '/' + coin.id + '/back';
  }
}
