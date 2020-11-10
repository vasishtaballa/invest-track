import { Component, EventEmitter, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-portfolio-item',
  templateUrl: './portfolio-item.component.html',
  styleUrls: ['./portfolio-item.component.css']
})
export class PortfolioItemComponent implements OnInit {

  equity: any;
  private event: EventEmitter<any> = new EventEmitter();

  constructor(public bsModalRef: BsModalRef) { }

  ngOnInit(): void {
    this.equity = {};
  }

  calculateAmounts(event: any): void {
    let id = event.target.id;
    if(id !== 'pbt')
      this.equity.pbt = (parseFloat(this.equity.price) * parseFloat(this.equity.qty)).toFixed(2);
    if(id !== 'brokerageAmount')
      this.equity.brokerageAmount = ((parseFloat(this.equity.pbt) * parseFloat(this.equity.brokerage)) / 100).toFixed(2);
    if(id !== 'net')
      this.equity.net = (parseFloat(this.equity.pbt) + parseFloat(this.equity.brokerageAmount) + parseFloat(this.equity.taxes)).toFixed(2);
  }

  addEquity(): void {
    this.event.emit(this.equity);
    this.bsModalRef.hide();
  }

}
