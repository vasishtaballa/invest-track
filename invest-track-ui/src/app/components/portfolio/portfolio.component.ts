import { Component, OnInit } from '@angular/core';
import { ColDef, FrameworkComponentWrapper, GridApi, GridOptions } from 'ag-grid-community';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { PortfolioColDefs } from 'src/app/constants/grid-col-defs/portfolio-col-defs.constant';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';
import { HttpService } from 'src/app/services/http/http.service';
import { SellBtnRendererComponent } from '../ag-grid-components/sell-btn-renderer/sell-btn-renderer.component';
import { PortfolioItemComponent } from '../portfolio-item/portfolio-item.component';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  private bsModalRef: BsModalRef;
  private equity: any = {};
  public rowData = [];
  public columnDefs: ColDef[];
  public gridApi: GridApi;
  public gridColumnApi;
  public frameworkComponents;
  public gridOptions: GridOptions;
  public defaultColDef: ColDef;

  constructor(private bsModalService: BsModalService, private httpService: HttpService) {
    this.defaultColDef = {
      sortable: true
    };
  }

  ngOnInit(): void {
    this.initialize();
  }

  initialize(): void {
    this.httpService.get(this.httpService.getCompleteURL(ServiceURLs.GET_PORTFOLIO))
      .subscribe(res => {
        this.columnDefs = PortfolioColDefs.COLUMN_DEFS;
        this.frameworkComponents = {
          sellBtnRenderer: SellBtnRendererComponent
        };
        this.gridOptions = {
          context: {
            componentParent: this
          }
        };
        let response = res.response;
        this.updatePortfolio(response);
      });
  }

  updatePortfolio(response) {
    let count = 0;
    response.forEach((item, index) => {
      this.httpService.get(this.getMCURL(ServiceURLs.MC_PRICE_API, item))
        .subscribe(res => {
          count++;
          item.currentPrice = parseFloat(res.data.pricecurrent);
          item.margin = this.getMargin(item.price, item.currentPrice);
          response[index] = item;
          if (count == response.length) {
            this.rowData = response;
          }
        });
    });
  }

  getMargin(costPrice, currentPrice): string {
    return (((currentPrice - costPrice) / costPrice) * 100).toFixed(2);
  }

  getMCURL(url: string, data: any): string {
    return url + data.exchange.toLowerCase() + '/equitycash/' + data.mcSymbol;
  }

  onGridReady(params): void {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.gridApi.sizeColumnsToFit();
  }

  addTrade(): void {
    const initialState = {
      equity: this.equity
    }
    this.bsModalRef = this.bsModalService.show(PortfolioItemComponent, { initialState, class: 'modal-lg' });
    this.bsModalRef.content.event.subscribe(res => {
      this.submitTrade(res);
    });
  }

  refreshPortfolio(data: any): void {
    this.initialize();
  }

  submitTrade(equity: any): void {
    this.httpService.post(this.httpService.getCompleteURL(ServiceURLs.ADD_TRADE), equity)
      .subscribe(res => {
        this.refreshPortfolio(res);
      })
  }

}
