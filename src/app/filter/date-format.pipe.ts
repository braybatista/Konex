import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormat'
})
export class DateFormatPipe implements PipeTransform {

  transform(date: string | Date): string {
    let yourDate = new Date(date);
    return yourDate.toISOString().split('T')[0];
  }

}
