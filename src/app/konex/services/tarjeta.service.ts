import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Tarjeta } from '../models/tarjeta';

@Injectable({
  providedIn: 'root'
})
export class TarjetaService {

  private endpoint = environment.endpointTarjetaService;

  constructor(public http: HttpClient) {}

  public consultarTarjetas(){
    return this.http.get(this.endpoint+"consultar");
  }

  public consultarTarjeta(tarjeta: Tarjeta){
    return this.http.get(this.endpoint+"consultar"+"/"+tarjeta.taIdentificador);
  }

  public crearTarjeta(tarjeta: Tarjeta){
    return this.http.post(this.endpoint+"crear", tarjeta);
  }

  public enrolarTarjeta(tarjeta: Tarjeta){
    return this.http.put(this.endpoint+"enrolar", tarjeta);
  }

  public eliminarTarjeta(tarjeta: Tarjeta){
    return this.http.delete(this.endpoint+"eliminar"+"/"+tarjeta.taIdentificador);
  }
}
