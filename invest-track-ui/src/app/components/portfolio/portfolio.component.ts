import { Component, OnInit } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { PortfolioItemComponent } from '../portfolio-item/portfolio-item.component';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  private bsModalRef: BsModalRef;
  private stock: any = {};
  rowData = [];

  constructor(private bsModalService: BsModalService) { }

  ngOnInit(): void {
  }

  addStock(): void {
    const initialState = {
      stock: this.stock
    }
    this.bsModalRef = this.bsModalService.show(PortfolioItemComponent, {initialState, class: 'modal-lg'});
    this.bsModalRef.content.event.subscribe(res => {
      console.log(res);
    });
  }

}
