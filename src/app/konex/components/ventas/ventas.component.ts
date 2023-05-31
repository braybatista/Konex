import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Transaccion } from '../../models/transaccion';
import { TransaccionService } from '../../services/transaccion.service';

declare var $: any;
@Component({
  selector: 'ventas',
  templateUrl: './ventas.component.html',
  styleUrls: ['./ventas.component.css']
})
export class VentasComponent implements OnInit {

  public selectedTransaccion: Transaccion = new Transaccion();
  public filterargs = {
    trNumReferencia : 0,
    trDireccionCompra : '',
    trTotalCompra : 0,
    trEstado : '',
    trFecha : '',
    trIdentificador : ''
  }
  public numberRows: number = 10;
  public numberPage: number = 1;
  public numRowsIni: number = 0;
  public numRowsEnd: number = this.numberRows;
  public pages:Array<number> = [];
  public transaccions: Transaccion[] = [];

  public modalCreateForm: FormGroup;

  constructor(private transaccionService: TransaccionService) {
    this.consultarTransacciones();
    this.modalCreateForm = new FormGroup({
      trNumReferencia: new FormControl(['', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.minLength(6), Validators.maxLength(6) ]]),
      trIdentificador: new FormControl(['', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.minLength(16), Validators.maxLength(30) ]]),
      trTotalCompra: new FormControl(['', [Validators.required, Validators.minLength(1), Validators.maxLength(10) ]]),
      trDireccionCompra: new FormControl(['', [Validators.required, Validators.minLength(1), Validators.maxLength(75) ]]),
    });
  }

  ngOnInit(): void {
    $('.modal').modal({dismissible: false});
    $('.tooltipped').tooltip({delay: 20});
  }

  consultarTransacciones(){
    this.transaccionService.consultarTransacciones().subscribe({
      next: (response: any)=>{
        console.log(response)
        this.transaccions = response.data;
        this.calculatePagesNumber();
      },
      complete: ()=>{
          
      },
      error: (error)=>{
        console.log(error)
      }
    });
  }
  
  mergeTransaccion(transaccion: Transaccion){
    if(confirm('Está seguro que quiere agregar la transaccion?')){
      this.transaccionService.crearTransaccion(this.selectedTransaccion).subscribe({
        next: (response: any)=>{
          console.log(response)
          if(response.codigoRespuesta !== "00"){
            alert("ha ocurrido un errror al tratar de crear el usuario")
          } else{
            alert("Creación Exitosa!!!")
          }
        },
        complete: () => {
          this.consultarTransacciones();
          this.limpiarTransaccion();
        },
        error: (error)=>{
          console.log(error)
          alert("Error: " + error.error.codigoRespuesta + " - " + error.error.mensaje);
          this.limpiarTransaccion();
        }
      });
    }
  }

  /* Delete Medicamento */
  eliminarTransaccion(transaccion: Transaccion){
    if(confirm('Está seguro que quiere Eliminar la transaccion?')){
      this.transaccionService.anularTransaccion(transaccion).subscribe({
        next: (response: any)=>{
          console.log(response)
          if(response.codigoRespuesta !== "00"){
            alert("ha ocurrido un errror al tratar de crear el usuario")
          } else{
            alert("Anulación Exitosa!!!")
          }
        },
        complete: ()=>{
          this.consultarTransacciones();
          this.limpiarTransaccion();
        },
        error: (error)=>{
          console.log(error)
          alert("Error: " + error.error.codigoRespuesta + " - " + error.error.mensaje);
          this.limpiarTransaccion();
        }
      });
    }
  }
  
  /* Load initial Transaccion */
  cargarTransaccion(Transaccion: Transaccion){
    this.selectedTransaccion = Transaccion;
  }

  calculatePagesNumber(){
    this.pages = [];
    var dataLength = this.transaccions.length;
    var numberRows = this.numberRows;
    var pageNum = Math.ceil(dataLength/numberRows)

    for(var i=1; i<=pageNum; i++){
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

  getActualDate(){
    const now = new Date();
    const offsetMs = now.getTimezoneOffset() * 60 * 1000;
    const dateLocal = new Date(now.getTime() - offsetMs);
    return dateLocal.toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");
  }

  limpiarTransaccion(){
    this.selectedTransaccion = new Transaccion();
  }

}
