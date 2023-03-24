import {Injectable} from "@angular/core";
import {OrderService} from "../components/inventory/order.service";
import {Order, Page, Pageable, RentContract, RentOrder} from "../model/navigation.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {BreadcrumbsService} from "../components/navigation/top-nav/breadcrumbs.service";
import {UrlBuilderService} from "../utils/UrlBuilder.service";

@Injectable()
export class OrderServiceImpl implements OrderService {
    constructor(protected http: HttpClient,
                protected breadService: BreadcrumbsService,
                protected urlBuilder: UrlBuilderService) {
    }

    placeOrder<ITEM extends Order>(order: ITEM): Observable<any> {
        return this.http.post(this.urlBuilder.getUrl("inventoryOrder"), order);
    }

    getOrdersByInventoryCode<ITEM extends Order[]>(code?: string): Observable<ITEM> {
        if (!code) {
            code = this.breadService.pages.details
        }
        return this.http.get<ITEM>(this.urlBuilder.getUrl("inventoryOrders", [{key: "code", value: code}]));
    }

    getOrdersByClientCode<ITEM extends Order[]>(code?: string): Observable<ITEM> {
        if (!code) {
            code = this.breadService.pages.details
        }
        return this.http.get<ITEM>(this.urlBuilder.getUrl("inventoryClientOrders", [{key: "code", value: code}]));
    }

    getOrdersByContractCode<ITEM extends Order[]>(code: string): Observable<ITEM> {
        return this.http.get<ITEM>(this.urlBuilder.getUrl("contractOrders", [{key: "code", value: code}]));
    }
}