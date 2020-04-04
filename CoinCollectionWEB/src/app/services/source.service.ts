import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Source } from '../models/source.Model';
import {GlobalService} from './global.service';

@Injectable({
  providedIn: 'root'
})
export class SourceService {
  sourcesUrl = '/sources';

  constructor(private http: HttpClient, private globalService: GlobalService) { }

  getAll(): Observable<Source[]> {
    return this.http.get<Source[]>(this.globalService.url + this.sourcesUrl);
  }
}
