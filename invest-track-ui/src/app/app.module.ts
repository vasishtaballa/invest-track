import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { TabsModule } from 'ngx-bootstrap/tabs'

import { AgGridModule } from 'ag-grid-angular';

import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from './components/header/header.component';
import { PortfolioComponent } from './components/portfolio/portfolio.component';
import { PortfolioItemComponent } from './components/portfolio-item/portfolio-item.component';
import { SellEquityModalComponent } from './components/sell-equity-modal/sell-equity-modal.component';
import { SellBtnRendererComponent } from './components/ag-grid-components/sell-btn-renderer/sell-btn-renderer.component';
import { TradingAmountComponent } from './components/trading-amount/trading-amount.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { InrCurrencyPipe } from './pipes/inr-currency/inr-currency.pipe';
import { PortfolioSummaryComponent } from './components/portfolio/portfolio-summary/portfolio-summary.component';
import { PortfolioDetailsComponent } from './components/portfolio/portfolio-details/portfolio-details.component';


const routes: Routes = [
  { path: 'portfolio', component: PortfolioComponent },
  { path: 'trading-amount', component: TradingAmountComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PortfolioComponent,
    PortfolioItemComponent,
    SellEquityModalComponent,
    SellBtnRendererComponent,
    TradingAmountComponent,
    DashboardComponent,
    InrCurrencyPipe,
    PortfolioSummaryComponent,
    PortfolioDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot(),
    AgGridModule.withComponents([]),
    TypeaheadModule.forRoot(),
    TabsModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }