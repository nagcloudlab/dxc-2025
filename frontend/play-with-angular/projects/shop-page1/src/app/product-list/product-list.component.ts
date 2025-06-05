import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { ProductComponent } from "../product/product.component";

@Component({
  selector: 'app-product-list',
  imports: [
    CommonModule,
    ProductComponent
],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent {

  products: Array<any> = [
    {
    id: 1,
    name: 'Laptop',
    price: 100000,
    isAvailable: true,
    isBuyable: true,
    description: 'This is a great laptop.',
    imageUrl: 'Laptop.png',
    },
    {
      id: 2,
      name: 'Smartphone',
      price: 50000,
      isAvailable: true,
      isBuyable: true,
      description: 'This is a great smartphone.',
      imageUrl: 'Mobile.png',
    },
  ];

  @Output("buy") buy = new EventEmitter<any>();
  
  handleBuy(event: any) {
    this.buy.emit(event);
  }

}
