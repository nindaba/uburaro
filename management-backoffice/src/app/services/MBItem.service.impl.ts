import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Item} from "../model/navigation.model";
import {BreadcrumbsService} from "../components/navigation/top-nav/breadcrumbs.service";
import {HttpClient} from "@angular/common/http";
import {UrlBuilderService} from "../utils/UrlBuilder.service";
import {MBItemService} from "./MBItem.service";

@Injectable()
export class MBItemServiceImpl implements MBItemService {

    constructor(
        private bread: BreadcrumbsService,
        private http: HttpClient,
        private urlBuilder: UrlBuilderService
    ) {
    }

    getItemByFacilityCode<ITEM extends Item[]>(): Observable<ITEM> {
        if (!this.bread.isFacilitySelected()) {
            return new Observable<ITEM>();
        }
        return this.http.get<ITEM>(this.urlBuilder.getFullUrl());
    }

    getItemByCode<ITEM extends Item>(code: string, allFields: boolean): Observable<ITEM> {
        if (!this.bread.isFacilitySelected()) {
            return new Observable<ITEM>();
        }
        return this.http.get<ITEM>(this.urlBuilder.getFullUrl(allFields));
    }

}