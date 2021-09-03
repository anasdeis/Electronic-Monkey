import { Component, Input, OnInit } from '@angular/core';
import { OrderProduct } from 'src/app/models/OrderProduct';
import { Product } from 'src/app/models/Product';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-product-item-computers',
  templateUrl: './product-item-computers.component.html',
  styleUrls: ['./product-item-computers.component.css']
})
export class ProductItemComputersComponent implements OnInit {

  @Input() productItem: Product;

  retrievedImage = '';
  productOrdersArray: OrderProduct[] = [];
  orderProduct: OrderProduct = new OrderProduct();
  isHidden: boolean;

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.retrievedImage = 'data:' + this.productItem.photo.type + ';base64,' + this.productItem.photo.image;
    this.isHidden = this.authenticationService.currentUserValue.admin;
  }

  handleAddToCart() {
    let productOrders = localStorage.getItem("productOrders");
    if (productOrders) {
      this.productOrdersArray = JSON.parse(productOrders);
    }
    this.orderProduct.product = this.productItem;
    this.orderProduct.quantity = 1;
    this.orderProduct.totalPrice = this.productItem.price;
    let index = this.productOrdersArray.findIndex(orderProduct => orderProduct.product.id === this.productItem.id);
    if(index > -1){
      this.productOrdersArray[index].quantity++;
    } else {
      this.productOrdersArray.push(this.orderProduct);
    }
    localStorage.setItem("productOrders", JSON.stringify(this.productOrdersArray));
    window.location.reload();
  }

  hidePost(){
    
  }
}