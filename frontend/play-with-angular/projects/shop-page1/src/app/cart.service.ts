import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  
  //  state
  private cartItems: any[] = [];
  cart$: any=new BehaviorSubject<any[]>(this.cartItems);

  constructor() { 
    // Initialize the cart service
    console.log("CartService initialized");
  }


  // state change logic
  addToCart(item: any) {
    this.cartItems.push(item);
    this.cart$.next(this.cartItems);
  }

  getCartItems() {
    return this.cartItems;
  }

}
