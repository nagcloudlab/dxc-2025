import { CommonModule, CurrencyPipe, UpperCasePipe } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { HighlightDirective } from '../highlight.directive';
import { DiscountPipe } from '../discount.pipe';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-product',
  imports: [
    CommonModule,
    CurrencyPipe,
    UpperCasePipe,
    DiscountPipe,
    HighlightDirective
  ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
  // providers: [
  //   CartService // Providing CartService at the component level
  // ]
})
export class ProductComponent {

  //cartService: any=new CartService();

  constructor(private cartService: CartService) {
    // Initialize the cart service
    console.log("ProductComponent initialized with CartService");
  }

  @Input("value") product: any;
  //@Output("buy") buy: any=new EventEmitter();

  currentTab: number = 1;
  isTabSelected(tabNumber: number): boolean {
    return this.currentTab === tabNumber;
  }
  handleTabChange(event:MouseEvent,tabNumber: number) {
    this.currentTab = tabNumber;
  }
  handleBuy(event: MouseEvent) { 
    //this.buy.emit(this.product);
    this.cartService.addToCart(this.product);
  }

  handleHighlightChange(event: string) {
    // console.log("In product component");
    // console.log('Highlight changed:', event);
    // You can perform additional actions here if needed
  }

}
