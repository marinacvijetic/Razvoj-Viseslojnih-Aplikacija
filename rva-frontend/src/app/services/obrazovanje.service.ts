import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OBRAZOVANJE_URI } from '../constants';
import { obrazovanje } from '../models/obrazovanje';

@Injectable({
  providedIn: 'root'
})
export class ObrazovanjeService {

  constructor(private httpClient: HttpClient) { }

  public getAllObrazovanje(): Observable<any> {
    return this.httpClient.get(`${OBRAZOVANJE_URI}`);
  }

  public addObrazovanje(obrazovanje: obrazovanje): Observable<any>{
    obrazovanje.idObrazovanje=0;
    return this.httpClient.post(`${OBRAZOVANJE_URI}`, obrazovanje);
  }

  public updateObrazovanje(obrazovanja: obrazovanje): Observable<any>{
    return this.httpClient.put(`${OBRAZOVANJE_URI}`, obrazovanja)
  }

  public deleteObrazovanje(id: number): Observable<any>{
    return this.httpClient.delete(`${OBRAZOVANJE_URI}/${id}`);
  }
}
