import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-product',
  imports: [
    CommonModule,
    CurrencyPipe
  ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {

  @Input("value") product: any;
  @Output("buy") buy: any=new EventEmitter();

  currentTab: number = 1;
  isTabSelected(tabNumber: number): boolean {
    return this.currentTab === tabNumber;
  }
  handleTabChange(event:MouseEvent,tabNumber: number) {
    this.currentTab = tabNumber;
  }
  handleBuy(event: MouseEvent) { 
    event.stopPropagation();
    this.buy.emit(this.product);
  }

}
