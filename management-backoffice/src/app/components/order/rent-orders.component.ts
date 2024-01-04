import {Component, Input} from '@angular/core';
import {RentContract, RentOrder} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {AbstractOrdersComponent} from "./abstract-orders.component";
import {Observable} from "rxjs";

@Component({
  selector: 'mb-rent-orders',
  templateUrl: './rent-orders.component.html'
})
export class RentOrdersComponent extends AbstractOrdersComponent<RentOrder>{
  @Input()
  contract:string = "";
  @Input()
  clientPage: boolean = false;
  orderHeads: string[] = DetailsConfig.contract.order.heads;

  getOrders(): Observable<RentOrder[]> {
      return this.orderService.getOrdersByContractCode<RentOrder[]>(this.contract);
  }
}
