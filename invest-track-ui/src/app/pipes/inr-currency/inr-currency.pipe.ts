import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'inrCurrency'
})
export class InrCurrencyPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): string {
    if (value != null) {
      return Number(value).toLocaleString('en-IN', {
        style: 'currency',
        currency: 'INR',
        maximumFractionDigits: 2
      });
    }
    return Number(0).toLocaleString('en-IN', {
      style: 'currency',
      currency: 'INR',
      maximumFractionDigits: 2
    });
  }

}
