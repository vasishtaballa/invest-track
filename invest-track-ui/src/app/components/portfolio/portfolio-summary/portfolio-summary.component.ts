import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ColDef, GridApi, GridOptions } from 'ag-grid-community';
import { Observable, Subscription } from 'rxjs';
import { PortfolioSummaryColDefs } from 'src/app/constants/grid-col-defs/portfolio-sum-col-defs.constant';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';
import { HttpService } from 'src/app/services/http/http.service';

@Component({
  selector: 'app-portfolio-summary',
  templateUrl: './portfolio-summary.component.html',
  styleUrls: ['./portfolio-summary.component.css']
})
export class PortfolioSummaryComponent implements OnInit, OnDestroy {

  public rowData = [];
  public columnDefs: ColDef[];
  public gridApi: GridApi;
  public gridColumnApi;
  public gridOptions: GridOptions;
  public defaultColDef: ColDef;
  private fitToSizeSubscription: Subscription;
  @Input() fitToSizeEvent: Observable<any>;

  constructor(private httpService: HttpService) {
    this.defaultColDef = {
      sortable: true
    };
  }

  ngOnInit(): void {
    this.initialize();
    this.fitToSizeSubscription = this.fitToSizeEvent.subscribe((event) => {
      this.gridApi.sizeColumnsToFit();
    })
  }

  initialize(): void {
    this.httpService.get(this.httpService.getCompleteURL(ServiceURLs.GET_PORTFOLIO_SUMMARY))
      .subscribe(res => {
        this.columnDefs = PortfolioSummaryColDefs.COLUMN_DEFS;
        this.gridOptions = {
          context: {
            componentParent: this
          }
        };
        this.rowData = res.response;
      });
  }

  onGridReady(params): void {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.gridApi.sizeColumnsToFit();
  }

  ngOnDestroy() {
    this.fitToSizeSubscription.unsubscribe();
  }
}
