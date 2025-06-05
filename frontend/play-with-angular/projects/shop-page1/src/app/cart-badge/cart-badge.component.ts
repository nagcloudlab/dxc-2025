import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-cart-badge',
  imports: [],
  templateUrl: './cart-badge.component.html',
  styleUrl: './cart-badge.component.css'
})
export class CartBadgeComponent {


  @Input("value") cartCount: number = 0;

}
