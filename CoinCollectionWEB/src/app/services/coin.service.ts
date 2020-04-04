import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Coin } from '../models/coin.Model';
import { Observable } from 'rxjs';
import {GlobalService} from './global.service';

const coinHttpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
const imageHttpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'image/*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CoinService {
  coinsUrl = '/coins';

  constructor(private http: HttpClient, private globalService: GlobalService) {}

  getAll(): Observable<Coin[]> {
    return this.http.get<Coin[]>(this.globalService.url + this.coinsUrl);
  }

  postCoin(coin: Coin): Observable<Coin> {
    return this.http.post<Coin>(this.globalService.url + this.coinsUrl, coin, coinHttpOptions);
  }

  postFront(coin: Coin, image: File): Observable<File> {
    return this.http.post<File>(this.globalService.url + this.getFrontImageUrl(coin), image, imageHttpOptions);
  }

  postBack(coin: Coin, image: File): Observable<File> {
    return this.http.post<File>(this.globalService.url + this.getBackImageUrl(coin), image, imageHttpOptions);
  }

  getFrontImageUrl(coin: Coin): string {
    return this.globalService.url + this.coinsUrl + '/' + coin.id + '/front';
  }

  getBackImageUrl(coin: Coin): string {
    return this.globalService.url + this.coinsUrl + '/' + coin.id + '/back';
  }
}
