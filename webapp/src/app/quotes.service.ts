import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import {  } from '@angular/common/http';

import { Quote } from './quote/quote'
import { Like } from './quote/like'
@Injectable({
  providedIn: 'root'
})
export class QuotesService {

  quotesUrl: string = '/api/quote'

  constructor(private http: HttpClient) { }

  getQuote(): Observable<Quote> {
    return this.http.get<Quote>(this.quotesUrl);
  }

  likeQuote(quote: any, user: any): void {
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };
    this.http.post<Like>(this.quotesUrl + "/like", { quote, user }, httpOptions).subscribe();
  }
}
