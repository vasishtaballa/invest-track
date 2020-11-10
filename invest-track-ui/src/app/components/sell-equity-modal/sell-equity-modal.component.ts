import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';
import { HttpService } from 'src/app/services/http/http.service';

@Component({
  selector: 'app-sell-equity-modal',
  templateUrl: './sell-equity-modal.component.html',
  styleUrls: ['./sell-equity-modal.component.css']
})
export class SellEquityModalComponent implements OnInit {

  buyEqty: any;
  sellEqty: any;

  constructor(public bsModalRef: BsModalRef, private httpService: HttpService) { }

  ngOnInit(): void {
    this.sellEqty = {};
  }

  calculateAmounts(event: any): void {
    let id = event.target.id;
    if (id !== 'pbt')
      this.sellEqty.pbt = (parseFloat(this.sellEqty.price) * parseFloat(this.sellEqty.qty)).toFixed(2);
    if (id !== 'brokerageAmount')
      this.sellEqty.brokerageAmount = ((parseFloat(this.sellEqty.pbt) * parseFloat(this.sellEqty.brokerage)) / 100).toFixed(2);
    if (id !== 'net')
      this.sellEqty.net = (parseFloat(this.sellEqty.pbt) + parseFloat(this.sellEqty.brokerageAmount) + parseFloat(this.sellEqty.taxes)).toFixed(2);
    this.sellEqty.margin = (((parseFloat(this.sellEqty.price) - parseFloat(this.buyEqty.price)) / parseFloat(this.buyEqty.price)) * 100).toFixed(2);
  }

  sellEquity(): void {
    this.sellEqty.buyTrade = this.buyEqty;
    this.httpService.post(this.httpService.getCompleteURL(ServiceURLs.SELL_EQUITY), this.sellEqty)
      .subscribe(res => {
        console.log(res);
      });
    this.bsModalRef.hide();
  }

}
