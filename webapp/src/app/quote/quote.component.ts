import { Component, OnInit } from '@angular/core';
import { QuotesService } from './../quotes.service';

@Component({
  selector: 'app-quote',
  templateUrl: './quote.component.html',
  styleUrls: ['./quote.component.css']
})
export class QuoteComponent implements OnInit {

   author: string | undefined;
   content: string | undefined;
   id: number | undefined;

   constructor(private quoteService: QuotesService) {}

   ngOnInit(): void {
      this.getQuote();
   }

   getQuote(): void {
      this.quoteService.getQuote()
        .subscribe(quote => {
           this.author = quote.author;
           this.content = quote.content;
           this.id = quote.id;
         });
   }

   like(): void {
      this.quoteService.likeQuote(this.id, "xd");
   }
}
