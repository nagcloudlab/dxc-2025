import { CommonModule, DatePipe } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { ProductComponent } from "../product/product.component";

@Component({
  selector: 'app-product-list',
  imports: [
    CommonModule,
    DatePipe,
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
    discount: 10,
    isAvailable: true,
    isBuyable: true,
    description: 'This is a great laptop.',
    imageUrl: 'Laptop.png',
    makeDate:Date.now(),
    },
    {
      id: 2,
      name: 'Smartphone',
      price: 50000,
      discount: 5,
      isAvailable: true,
      isBuyable: true,
      description: 'This is a great smartphone.',
      imageUrl: 'Mobile.png',
      makeDate:Date.now(),
    },
  ];

  // @Output("buy") buy = new EventEmitter<any>();
  
  // handleBuy(event: any) {
  //   this.buy.emit(event);
  // }

}
