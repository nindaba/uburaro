import {Order} from "../../model/navigation.model";
import {Observable} from "rxjs";

export abstract class OrderService {
    /**
     * Places the order to the back end
     *
     * @param order
     */
    abstract placeOrder<ITEM extends Order>(order: ITEM): Observable<any>;

    /**
     * Gets all the orders that belong to the inventory {@link code}
     *
     * @param code
     * @return observable array of orders
     */
    abstract getOrdersByInventoryCode<ITEM extends Order[]>(code:string): Observable<ITEM>;

    /**
     * Gets all the orders that belong to the client {@link code}
     *
     * @param code
     */
    abstract getOrdersByClientCode<ITEM extends Order[]>(code:string): Observable<ITEM>;
}