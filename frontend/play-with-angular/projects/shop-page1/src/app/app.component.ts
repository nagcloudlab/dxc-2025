import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  imports:[CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'shop-page1';
  products: Array<any> = [
    {
    id: 1,
    name: 'Laptop',
    price: 100000,
    isAvailable: true,
    isBuyable: false,
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
  currentTab: number = 1;
  isTabSelected(tabNumber: number): boolean {
    return this.currentTab === tabNumber;
  }
  handleTabChange(event:MouseEvent,tabNumber: number) {
    this.currentTab = tabNumber;
  }
  handleBuy(event: MouseEvent) { 
    console.log(event)
    console.log('Product added to cart!');
  }

}
