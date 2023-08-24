import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PREDUZECE_URI } from '../constants';
import { preduzece } from '../models/preduzece';

@Injectable({
  providedIn: 'root'
})
export class PreduzeceService {

  constructor(private httpClient: HttpClient) { }

  public getAllPreduzece(): Observable<any>{
    return this.httpClient.get(`${PREDUZECE_URI}`);
  }

  public addPreduzece(preduzece: preduzece): Observable<any>{
    preduzece.idPreduzece=0;
    return this.httpClient.post(`${PREDUZECE_URI}`, preduzece);
  }

  public updatePreduzece(preduzece: preduzece): Observable<any>{
    return this.httpClient.put(`${PREDUZECE_URI}`, preduzece)
  }

  public deletePreduzece(id: number): Observable<any>{
    return this.httpClient.delete(`${PREDUZECE_URI}/${id}`);
  }
}
