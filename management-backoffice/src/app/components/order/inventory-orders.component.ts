import {Component, Input} from '@angular/core';
import {Observable} from "rxjs";
import {InventoryOrder} from "../../model/navigation.model";
import DetailsConfig from "../../../assets/content-config/details-page.json";

@Component({
  selector: 'mb-inventory-orders',
  templateUrl: './inventory-orders.component.html'
})
export class InventoryOrdersComponent {

    @Input("orders")
    $inventoryOrders: Observable<InventoryOrder[]> = new Observable();

    orderHeads: string[] = DetailsConfig.inventory.order.heads;
    @Input()
    clientPage: boolean = false;

}
