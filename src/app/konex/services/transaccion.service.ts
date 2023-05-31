import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Transaccion } from '../models/transaccion';

@Injectable({
  providedIn: 'root'
})
export class TransaccionService {

  private endpoint = environment.endpointTransaccionService;

  constructor(public http: HttpClient) {}

  public consultarTransacciones(){
    return this.http.get(this.endpoint+"consultar");
  }

  public consultarTransaccione(trNumReferencia: number){
    return this.http.get(this.endpoint+"consultar"+"/"+trNumReferencia);
  }

  public crearTransaccion(transaccion: Transaccion){
    return this.http.post(this.endpoint+"crear", transaccion);
  }

  public anularTransaccion(transaccion: Transaccion){
    return this.http.put(this.endpoint+"anular", transaccion);
  }
}
