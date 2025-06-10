import { CommonModule } from '@angular/common';
import { Component, ContentChild, Input, SimpleChanges, ViewChild } from '@angular/core';
import { HighlightDirective } from '../highlight.directive';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart-view',
  imports: [
    CommonModule,
    HighlightDirective
  ],
  templateUrl: './cart-view.component.html',
  styleUrl: './cart-view.component.css'
})
export class CartViewComponent {

  // @Input("value")
  cart!: Array<any>;
  @ContentChild("header") header!: any;
  @ViewChild("cardHeader") cartHeader!: any;

  constructor(private cartService: CartService) { 
    console.log("CartViewComponent :: constructor called");
    //console.log(this.cart);
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log("CartViewComponent :: ngOnChanges called");
    console.log(changes);
    // todo any side-effect(s) nased current & previous input values
  }

  ngOnInit() {
    console.log("CartViewComponent :: ngOnInit called");
    console.log(this.cart);
    // todo any one-time initialization logic based first input value
    // n/w calls, API calls, etc.
    // subscribe to any observable(s) here
     this.cartService.cart$.subscribe((cart: Array<any>) => {
      console.log("CartViewComponent :: cartService.cart$ subscription called");
      this.cart = cart;
    });

  }



  ngAfterContentInit() {
    console.log("CartViewComponent :: ngAfterContentInit called");
    if(this.header) {
      console.log("CartViewComponent :: cartHeader is available");
      console.log(this.header);
    } else {
      console.log("CartViewComponent :: cartHeader is NOT available");
    }
  }

  ngAfterViewInit() {
    console.log("CartViewComponent :: ngAfterViewInit called");
    if (!this.header) {
      this.cartHeader.nativeElement.innerHTML = "<h1>View Header</h1>";
    }
  }


  // // Angular Change Detection Strategy 
  
  // ngDoCheck() { }
  // ngAfterContentChecked() {
  //   console.log("CartViewComponent :: ngAfterContentChecked called");
  //   // todo any side-effect(s) based on content changes
  // }
  // ngAfterViewChecked() {
  //   console.log("CartViewComponent :: ngAfterViewChecked called");
  //   // todo any side-effect(s) based on view changes
  // }

  

  ngOnDestroy() {
    console.log("CartViewComponent :: ngOnDestroy called");
    // todo any cleanup logic
    // unsubscribe from any observable(s) here
  }


}
