import { Component, OnInit } from '@angular/core';
import { ColDef, GridApi, GridOptions } from 'ag-grid-community';
import { EquityPLColumnDefs } from 'src/app/constants/grid-col-defs/equity-pl-col-defs.constant';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';
import { HttpService } from 'src/app/services/http/http.service';

@Component({
  selector: 'app-equity-pl',
  templateUrl: './equity-pl.component.html',
  styleUrls: ['./equity-pl.component.css']
})
export class EquityPlComponent implements OnInit {

  total = 0;
  rowData = [];
  public columnDefs: ColDef[];
  public gridApi: GridApi;
  public gridColumnApi;
  public gridOptions: GridOptions;
  public defaultColDef: ColDef;

  constructor(private httpService: HttpService) {
    this.defaultColDef = {
      sortable: true
    };
  }

  ngOnInit(): void {
    this.initialize();
  }

  initialize(): void {
    this.httpService.get(this.httpService.getCompleteURL(ServiceURLs.GET_EQUITY_PL))
      .subscribe(res => {
        this.rowData = res.response;
        this.calculateTotal();
        this.columnDefs = EquityPLColumnDefs.COLUMN_DEFS;
        this.gridOptions = {
          context: {
            componentParent: this
          }
        };
      });

  }
  calculateTotal() {
    this.rowData.forEach(data => {
      this.total += data.netPL;
    })
  }

  onGridReady(params): void {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.gridApi.sizeColumnsToFit();
  }

}
