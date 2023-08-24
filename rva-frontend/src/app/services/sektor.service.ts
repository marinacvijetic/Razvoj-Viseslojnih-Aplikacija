import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SEKTOR_URI } from '../constants';
import { sektor } from '../models/sektor';

@Injectable({
  providedIn: 'root'
})
export class SektorService {

  constructor(private httpClient: HttpClient) { }

  public getAllSektor(): Observable<any> {

    return this.httpClient.get(`${SEKTOR_URI}`);

  }

  public addSektor(sektor: sektor): Observable<any>{
    sektor.idSektor=0;
    return this.httpClient.post(`${SEKTOR_URI}`, sektor);
  }

  public updateSektor(sektor: sektor): Observable<any>{
    return this.httpClient.put(`${SEKTOR_URI}`, sektor)
  }

  public deleteSektor(id: number): Observable<any>{
    return this.httpClient.delete(`${SEKTOR_URI}/${id}`);
  }
}
