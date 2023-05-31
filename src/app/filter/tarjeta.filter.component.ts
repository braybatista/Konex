import { Pipe, PipeTransform  } from '@angular/core';
import { Tarjeta } from '../konex/models/tarjeta';

@Pipe({
    name: 'tarjetafilter',
    pure: false
})
export class TarjetaPipe implements PipeTransform {
  transform(items: any[], filter: Tarjeta): any {  
    if (!items || !filter) {  
        return items;  
    }
    
    return items.filter(item => {
      console.log(this.cleanFields(filter.taTitular+""));
      return this.cleanFields(item.taNumValidacion+"").indexOf(this.cleanFields(filter.taTitular+"")) !== -1
      || this.cleanFields(item.taTipo+"").indexOf(this.cleanFields(filter.taTitular+"")) !== -1
      || this.cleanFields(item.taCedula+"").indexOf(this.cleanFields(filter.taTitular+"")) !== -1
      || this.cleanFields(item.taIdentificador+"").indexOf(this.cleanFields(filter.taTitular+"")) !== -1
      || this.cleanFields(item.taTelefono+"").indexOf(this.cleanFields(filter.taTitular+"")) !== -1
      || this.cleanFields(item.taTitular+"").indexOf(this.cleanFields(filter.taTitular+"")) !== -1
      || this.cleanFields(item.taEstado+"").indexOf(this.cleanFields(filter.taTitular+"")) !== -1
      || this.cleanFields(item.taPan+"").indexOf(this.cleanFields(filter.taTitular+"")) !== -1;
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