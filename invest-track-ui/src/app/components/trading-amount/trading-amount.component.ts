import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trading-amount',
  templateUrl: './trading-amount.component.html',
  styleUrls: ['./trading-amount.component.css']
})
export class TradingAmountComponent implements OnInit {

  deposit: any;

  constructor() { }

  ngOnInit(): void {
    this.deposit = {};
  }

  addTransaction(): void {

  }

}
