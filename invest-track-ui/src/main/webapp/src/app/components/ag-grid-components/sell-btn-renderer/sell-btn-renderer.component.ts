import { Component, OnInit } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams, IAfterGuiAttachedParams } from 'ag-grid-community';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { SellEquityModalComponent } from '../../sell-equity-modal/sell-equity-modal.component';

@Component({
  selector: 'app-sell-btn-renderer',
  templateUrl: './sell-btn-renderer.component.html',
  styleUrls: ['./sell-btn-renderer.component.css']
})
export class SellBtnRendererComponent implements ICellRendererAngularComp, OnInit {

  params: ICellRendererParams;
  bsModalRef: BsModalRef;

  constructor(private bsModalService: BsModalService) { }

  refresh(params: any): boolean {
    throw new Error('Method not implemented.');
  }

  agInit(params: any): void {
    this.params = params;
  }

  afterGuiAttached?(params?: IAfterGuiAttachedParams): void {
    throw new Error('Method not implemented.');
  }

  ngOnInit(): void {
  }

  sellEquity(item: any): void {
    let initialState = {
      buyEqty: this.params.data
    }
    this.bsModalRef = this.bsModalService.show(SellEquityModalComponent, { initialState, class: 'modal-lg' });
    this.bsModalRef.onHide.subscribe(res => {
      this.params.context.componentParent.refreshPortfolio(this.params);
    })
  }

}
