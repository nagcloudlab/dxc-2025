import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NavbarComponent } from "./navbar/navbar.component";
import { ProductListComponent } from "./product-list/product-list.component";
import { CartBadgeComponent } from "./cart-badge/cart-badge.component";
import { CartViewComponent } from "./cart-view/cart-view.component";
import { CartService } from './cart.service';


@Component({
  selector: 'app-root',
  imports: [CommonModule, NavbarComponent, ProductListComponent, CartBadgeComponent, CartViewComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  // providers: [
  //   CartService
  // ],
})
export class AppComponent {
  title = 'shop-page1';
  //cart: Array<any> = [];
  isCartOpen = false;
  toggleCart() {
    this.isCartOpen = !this.isCartOpen;
  }
  // handleBuy(product: any) {
  //   //this.cart.push(product); // mutating the cart
  //   this.cart = [...this.cart, product]; // immutable way of adding product to cart
  // }
}
