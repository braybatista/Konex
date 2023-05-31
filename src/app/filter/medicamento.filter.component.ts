import { Pipe, PipeTransform  } from '@angular/core';
import { Medicamento } from '../konex/models/medicamento';

@Pipe({
    name: 'medicamentofilter',
    pure: false
})
export class MedicamentoPipe implements PipeTransform {
  transform(items: any[], filter: Medicamento): any {  
    if (!items || !filter) {  
        return items;  
    }
    
    return items.filter(item => {
      console.log(this.cleanFields(filter.nombre+""));
      return this.cleanFields(item.nombre+"").indexOf(this.cleanFields(filter.nombre+"")) !== -1
      || this.cleanFields(item.laboratorio+"").indexOf(this.cleanFields(filter.nombre+"")) !== -1
      || this.cleanFields(this.formatDate(item.fechaFabricacion)).indexOf(this.cleanFields(filter.nombre+"")) !== -1
      || this.cleanFields(this.formatDate(item.fechaVencimiento)).indexOf(this.cleanFields(filter.nombre+"")) !== -1
      || this.cleanFields(item.stock+"").indexOf(this.cleanFields(filter.nombre+"")) !== -1
      || this.cleanFields(item.valorUnitario+"").indexOf(this.cleanFields(filter.nombre+"")) !== -1;
    }); 
  }

  formatDate(date: string): string{
    let yourDate = new Date(date);
    return yourDate.toISOString().split('T')[0];
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