import { Component, OnInit } from '@angular/core';
import { ColDef, GridApi, GridOptions } from 'ag-grid-community';
import { StatementColDefs } from 'src/app/constants/grid-col-defs/statement-col-defs.constant';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';
import { HttpService } from 'src/app/services/http/http.service';

@Component({
  selector: 'app-statement',
  templateUrl: './statement.component.html',
  styleUrls: ['./statement.component.css']
})
export class StatementComponent implements OnInit {

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
    this.httpService.get(this.httpService.getCompleteURL(ServiceURLs.GET_STATEMENT))
      .subscribe(res => {
        this.rowData = res.response;
        this.columnDefs = StatementColDefs.COLUMN_DEFS;
        this.gridOptions = {
          context: {
            componentParent: this
          }
        };
      });
  }

  onGridReady(params): void {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.gridApi.sizeColumnsToFit();
  }

}
