import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {


  eventsSubject: Subject<any> = new Subject<any>();

  constructor() { }

  ngOnInit(): void { }

  tabSelect(event: any): void {
    this.eventsSubject.next(event);
  }

}
