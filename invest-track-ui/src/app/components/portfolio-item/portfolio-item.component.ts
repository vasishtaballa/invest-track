import { Component, EventEmitter, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';
import { TypeHead } from 'src/app/models/type-head/type-head.model';
import { HttpService } from 'src/app/services/http/http.service';

@Component({
  selector: 'app-portfolio-item',
  templateUrl: './portfolio-item.component.html',
  styleUrls: ['./portfolio-item.component.css']
})
export class PortfolioItemComponent implements OnInit {

  equity: any;
  private event: EventEmitter<any> = new EventEmitter();
  typeHead: TypeHead;

  constructor(public bsModalRef: BsModalRef, private httpService: HttpService) { }

  ngOnInit(): void {
    this.equity = {};
    this.typeHead = {
      equities: [],
      sectors: [],
      exchanges: [],
      equitySymbols: [],
      mcEquitySymbols: []
    };
    this.httpService.get(this.httpService.getCompleteURL(ServiceURLs.GET_TYPEHEAD_DATA))
      .subscribe(res => {
        this.typeHead = res.response;
      });
  }

  calculateAmounts(event: any): void {
    let id = event.target.id;
    if (id !== 'pbt')
      this.equity.pbt = (parseFloat(this.equity.price) * parseFloat(this.equity.qty)).toFixed(2);
    if (id !== 'brokerageAmount')
      this.equity.brokerageAmount = ((parseFloat(this.equity.pbt) * parseFloat(this.equity.brokerage)) / 100).toFixed(2);
    if (id !== 'net')
      this.equity.net = (parseFloat(this.equity.pbt) + parseFloat(this.equity.brokerageAmount) + parseFloat(this.equity.taxes)).toFixed(2);
    if (id !== 'brokerage')
      this.equity.brokerage = ((parseFloat(this.equity.brokerageAmount) * 100) / parseFloat(this.equity.pbt)).toFixed(2);
  }

  addEquity(): void {
    this.event.emit(this.equity);
    this.bsModalRef.hide();
  }

}
