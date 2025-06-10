import { Component, Input } from '@angular/core';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart-badge',
  imports: [],
  templateUrl: './cart-badge.component.html',
  styleUrl: './cart-badge.component.css'
})
export class CartBadgeComponent {

  //@Input("value") 
  cartCount: number = 0;

  constructor(private cartService: CartService) { }
  
  ngOnInit() {
    console.log("CartBadgeComponent initialized");
    // Subscribe to the cart service to get the cart count
    this.cartService.cart$.subscribe((items: any[]) => {
      this.cartCount = items.length;
      console.log("Cart count updated:", this.cartCount);
    });
  }

}
