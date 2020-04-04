import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Collector } from '../models/collector.Model';
import {GlobalService} from './global.service';

@Injectable({
  providedIn: 'root'
})
export class CollectorService {
  collectorsUrl = '/collectors';

  constructor(private http: HttpClient, private globalService: GlobalService) { }

  getAll(): Observable<Collector[]> {
    return this.http.get<Collector[]>(this.globalService.url + this.collectorsUrl);
  }
}
