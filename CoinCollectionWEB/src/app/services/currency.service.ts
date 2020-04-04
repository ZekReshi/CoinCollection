import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Currency } from '../models/currency.Model';
import { Observable } from 'rxjs';
import {GlobalService} from './global.service';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  currenciesUrl = '/currencies';

  constructor(private http: HttpClient, private globalService: GlobalService) { }

  getAll(): Observable<Currency[]> {
    return this.http.get<Currency[]>(this.globalService.url + this.currenciesUrl);
  }
}
