import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceURLs } from 'src/app/constants/service-urls.constant';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpClient: HttpClient) { }

  get(url: string): Observable<any> {
    return this.httpClient.get(url);
  }

  post(url: string, body: any): Observable<any> {
    return this.httpClient.post(url, body);
  }

  getCompleteURL(url): string {
    return ServiceURLs.BASE_URL + url;
  }
}
