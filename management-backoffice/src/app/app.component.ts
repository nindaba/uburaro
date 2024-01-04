import {Component, OnInit} from '@angular/core';
import {OrderService} from "./components/inventory/order.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit{
  title = 'backoffice';
  constructor(private orderService:OrderService) {
  }
  ngOnInit(): void {
    this.orderService.createRentOrders().subscribe();
  }
}
