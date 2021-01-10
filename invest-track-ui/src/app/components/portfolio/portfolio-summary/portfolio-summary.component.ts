import { Component, OnInit } from '@angular/core';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';
import { HttpService } from 'src/app/services/http/http.service';

@Component({
  selector: 'app-portfolio-summary',
  templateUrl: './portfolio-summary.component.html',
  styleUrls: ['./portfolio-summary.component.css']
})
export class PortfolioSummaryComponent implements OnInit {

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.initialize();
  }

  initialize(): void {
    this.httpService.get(this.httpService.getCompleteURL(ServiceURLs.GET_PORTFOLIO_SUMMARY))
      .subscribe(res => {
        console.log(res);
      });
  }

}
