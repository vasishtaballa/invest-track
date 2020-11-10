import { Component, OnInit } from '@angular/core';
import { ColDef, GridApi, GridOptions } from 'ag-grid-community';
import { DepositsColDefs } from 'src/app/constants/grid-col-defs/deposits-col-defs.constant';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';
import { HttpService } from 'src/app/services/http/http.service';

@Component({
  selector: 'app-trading-amount',
  templateUrl: './trading-amount.component.html',
  styleUrls: ['./trading-amount.component.css']
})
export class TradingAmountComponent implements OnInit {

  deposit: any;
  rowData: any[] = [];
  public columnDefs: ColDef[];
  public gridApi: GridApi;
  public gridColumnApi;
  public gridOptions: GridOptions;

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.deposit = {};
    this.getDeposits();
  }

  onGridReady(params): void {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.gridApi.sizeColumnsToFit();
  }

  getDeposits(): void {
    this.httpService.get(this.httpService.getCompleteURL(ServiceURLs.GET_DEPOSITS))
      .subscribe(res => {
        this.rowData = res.response;
        this.columnDefs = DepositsColDefs.COLUMN_DEFS;
      })
  }

  addTransaction(): void {
    this.httpService.post(this.httpService.getCompleteURL(ServiceURLs.ADD_DEPOSIT), this.deposit)
      .subscribe(res => {
        this.getDeposits();
      });
  }

}
