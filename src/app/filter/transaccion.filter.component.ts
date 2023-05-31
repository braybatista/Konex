import { Pipe, PipeTransform  } from '@angular/core';
import { Transaccion } from '../konex/models/transaccion';

@Pipe({
    name: 'transaccionfilter',
    pure: false
})
export class TransaccionPipe implements PipeTransform {
  transform(items: any[], filter: Transaccion): any {  
    if (!items || !filter) {  
        return items;  
    }
    
    return items.filter(item => {
      console.log(this.cleanFields(filter.trIdentificador));
      return this.cleanFields(item.trNumReferencia+"").indexOf(this.cleanFields(filter.trIdentificador+"")) !== -1
      || this.cleanFields(item.trDireccionCompra+"").indexOf(this.cleanFields(filter.trIdentificador+"")) !== -1
      || this.cleanFields(item.trTotalCompra+"").indexOf(this.cleanFields(filter.trIdentificador+"")) !== -1
      || this.cleanFields(item.trEstado+"").indexOf(this.cleanFields(filter.trIdentificador+"")) !== -1
      || this.cleanFields(item.trFecha+"").indexOf(this.cleanFields(filter.trIdentificador+"")) !== -1
      || this.cleanFields(item.trIdentificador+"").indexOf(this.cleanFields(filter.trIdentificador+"")) !== -1
    });  
  }

  cleanFields(field: string){
    return field.toLowerCase()
    .trim()
    .replace(/ /g, "")
    .replace(/[á]/ig, "a")
    .replace(/[é]/ig, "e")
    .replace(/[í]/ig, "i")
    .replace(/[ó]/ig, "o")
    .replace(/[ú]/ig, "u");
  }
}