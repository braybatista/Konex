import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Tarjeta } from '../../models/tarjeta';
import { TarjetaService } from '../../services/tarjeta.service';

declare var $: any;

@Component({
  selector: 'medicamentos',
  templateUrl: './medicamentos.component.html',
  styleUrls: ['./medicamentos.component.css']
})
export class MedicamentosComponent implements OnInit {

  public selectedTarjeta: Tarjeta = new Tarjeta();
  public filterargs = {
    taNumValidacion : 0,
    taTipo : '',
    taCedula : 0,
    taIdentificador : '',
    taTelefono : 0,
    taTitular : '',
    taEstado : '',
    taPan : ''
  }
  public numberRows: number = 10;
  public numberPage: number = 1;
  public numRowsIni: number = 0;
  public numRowsEnd: number = this.numberRows;
  public pages:Array<number> = [];
  public tarjetas: Tarjeta[] = [];

  public modalCreateForm: FormGroup;
  public modalEnroleForm: FormGroup;

  constructor(
    private tarjetaService: TarjetaService
  ) {
    this.modalCreateForm = new FormGroup({
      taTitular: new FormControl(['', [Validators.required, Validators.minLength(5), Validators.maxLength(75) ]]),
      taTipo: new FormControl(['', [Validators.required, Validators.minLength(1), Validators.maxLength(10) ]]),
      taCedula: new FormControl(['', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.minLength(10), Validators.maxLength(15) ]]),
      taTelefono: new FormControl(['', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.minLength(10), Validators.maxLength(10) ]]),
      taPan: new FormControl(['',[Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.minLength(16), Validators.maxLength(19) ]]),
    });

    this.modalEnroleForm = new FormGroup({
      taNumValidacion: new FormControl(['', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.minLength(1), Validators.maxLength(3) ]]),
    });

    this.consultarTarjetas();
  }

  ngOnInit(): void {
    $('.modal').modal({dismissible: false});
    $('.tooltipped').tooltip({delay: 20});
  }

  consultarTarjetas(){
    this.tarjetaService.consultarTarjetas().subscribe({
      next: (response: any)=>{
        console.log(response)
        this.tarjetas = response.data;
        this.calculatePagesNumber();
      },
      complete: ()=>{
          
      },
      error: (error)=>{
        console.log(error)
      }
    });
  }
  
  /* Add or Edit Tarjeta */
  mergeTarjeta(tarjeta: Tarjeta){
    if(confirm('Está seguro que quiere agregar la tarjeta del usuario ' + tarjeta.taTitular)){
      this.tarjetaService.crearTarjeta(this.selectedTarjeta).subscribe({
        next: (response: any) => {
          console.log(response)
          if(response.codigoRespuesta !== "00"){
            alert("ha ocurrido un errror al tratar de crear el usuario")
          } else{
            alert("Creación Exitosa!!!")
          }
        },
        complete: () => {
          this.consultarTarjetas();
          this.limpiarTarjeta();
        },
        error: (error) => {
          console.log(error);
          alert("Error: " + error.error.codigoRespuesta + " - " + error.error.mensaje);
          // this.consultarTarjetas();
          this.limpiarTarjeta();
        }
      });
    }
  }
  
  /* Delete Tarjeta */
  eliminarTarjeta(tarjeta: Tarjeta){
    if(confirm('Está seguro que quiere Eliminar la tarjeta?')){
      this.tarjetaService.eliminarTarjeta(tarjeta).subscribe({
        next: (response: any) => {
          console.log(response)
          if(response.codigoRespuesta !== "00"){
            alert("ha ocurrido un errror al tratar de crear el usuario")
          } else{
            alert("Desactivación Exitoso!!!")
          }
        },
        complete: () => {
          this.consultarTarjetas();
          this.limpiarTarjeta();
        },
        error: (error) => {
          console.log(error)
          alert("Error: " + error.error.codigoRespuesta + " - " + error.error.mensaje);
          // this.consultarTarjetas();
          this.limpiarTarjeta();
        }
      });
    }
  }

  /* Delete Tarjeta */
  enrolarTarjeta(tarjeta: Tarjeta){
    if(confirm('Está seguro que quiere Enrolar la tarjeta?')){
      this.tarjetaService.enrolarTarjeta(tarjeta).subscribe({
        next: (response: any) => {
          console.log(response)
          if(response.codigoRespuesta !== "00"){
            alert("ha ocurrido un errror al tratar de enrolar la tarjeta")
          } else{
            alert("Enrolamiento Exitoso!!!")
          }
        },
        complete: () => {
          this.consultarTarjetas();
          this.limpiarTarjeta();
        },
        error: (error) => {
          console.log(error);
          alert("Error: " + error.error.codigoRespuesta + " - " + error.error.mensaje);
          // this.consultarTarjetas();
          this.limpiarTarjeta();
        }
      });
    }
  }

  limpiarTarjeta(){
    this.selectedTarjeta = new Tarjeta();
  }
  
  cargarTarjeta(tarjeta: Tarjeta){
    this.selectedTarjeta = tarjeta;
    this.selectedTarjeta.taNumValidacion = undefined;
  }

  calculatePagesNumber(){
    this.pages = [];
    let dataLength = this.tarjetas.length;
    let numberRows = this.numberRows;
    let pageNum = Math.ceil(dataLength/numberRows)
    for(let i=1; i<=pageNum; i++){
      this.pages.push(i);
    }
  }

  changeRowsSelect(){
    this.calculatePagesNumber();
    this.numRowsIni = 0;
    this.numRowsEnd = this.numberRows;
  }

  changePageSelect(){
    if(this.numberPage == 1){
      this.changeRowsSelect();
    } else{
      this.numRowsIni = parseInt(this.numberRows+"");
      this.numRowsEnd = this.numberRows * 2;
    }
  }
}
