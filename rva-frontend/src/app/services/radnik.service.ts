import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RADNIK_BY_SEKTOR_URI, RADNIK_URI } from '../constants';
import { radnik } from '../models/radnik';

@Injectable({
  providedIn: 'root'
})
export class RadnikService {

  constructor(private httpClient: HttpClient) { }

  public getRadnikBySektor(idSektor: number): Observable<any>{
    return this.httpClient.get(`${RADNIK_BY_SEKTOR_URI}/${idSektor}`)
  }

  public addRadnik(radnik: radnik): Observable<any>{
    radnik.idRadnik=0;
    return this.httpClient.post(`${RADNIK_URI}`, radnik);
  }

  public updateRadnik(radnik: radnik): Observable<any>{
    return this.httpClient.put(`${RADNIK_URI}`, radnik)
  }

  public deleteRadnik(id: number): Observable<any>{
    return this.httpClient.delete(`${RADNIK_URI}/${id}`);
  }

}
