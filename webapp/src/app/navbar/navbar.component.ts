import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  @Output() nextQuote = new EventEmitter<string>();

  random(): void {
    this.nextQuote.emit("");
  }
}
