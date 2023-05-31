import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IndexComponent } from './components/index/index.component';
import { MedicamentosComponent } from './components/medicamentos/medicamentos.component';
import { VentasComponent } from './components/ventas/ventas.component';
import { BrowserModule } from '@angular/platform-browser';
import { TarjetaPipe } from '../filter/tarjeta.filter.component';
import { TransaccionPipe } from '../filter/transaccion.filter.component';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [
    MedicamentosComponent,
    VentasComponent,
    IndexComponent,
    TarjetaPipe,
    TransaccionPipe,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FormsModule, 
    ReactiveFormsModule
  ],
  providers: [],
  schemas: [NO_ERRORS_SCHEMA]
})
export class KonexAppModule { }
