import { Component, OnInit } from '@angular/core';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';
import { Dashboard } from 'src/app/models/dashboard/dashboard.model';
import { HttpService } from 'src/app/services/http/http.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  dashboard: Dashboard;

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.getDashboardDetails();
  }

  getDashboardDetails(): void {
    this.httpService.get(this.httpService.getCompleteURL(ServiceURLs.GET_DASHBOARD))
    .subscribe(res => {
      this.dashboard = res.response;
    });
  }

}
