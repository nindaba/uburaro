import {Component, Input} from '@angular/core';
import {RentContract, RentOrder} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";
import {AbstractOrdersComponent} from "./abstract-orders.component";

@Component({
  selector: 'mb-rent-orders',
  templateUrl: './rent-orders.component.html'
})
export class RentOrdersComponent extends AbstractOrdersComponent{
  @Input()
  orders: RentOrder[] = [];
  @Input()
  clientPage: boolean = false;
  orderHeads: string[] = DetailsConfig.contract.order.heads;

}
