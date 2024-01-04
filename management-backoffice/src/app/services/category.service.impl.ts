import {CategoryService} from "../components/category/category.service";
import {Injectable} from "@angular/core";
import {Observable, tap} from "rxjs";
import {Category, EndpointConfig} from "../model/navigation.model";
import {BreadcrumbsService} from "../components/navigation/top-nav/breadcrumbs.service";
import {HttpClient} from "@angular/common/http";
import {UrlBuilderService} from "../utils/UrlBuilder.service";

@Injectable()
export class CategoryServiceImpl implements CategoryService {

    constructor(
        private bread: BreadcrumbsService,
        private http: HttpClient,
        private endpoint: EndpointConfig,
        private urlBuilder: UrlBuilderService
    ) {
    }

    getCategoriesByFacilityCode(): Observable<Category[]> {
        if (!this.bread.isFacilitySelected()) {
            return new Observable<Category[]>();
        }

        return this.http.get<Category[]>(this.urlBuilder.getFullUrl());
    }


    getCategoryByCode(code: string): Observable<Category> {
        if (!this.bread.isFacilitySelected()) {
            return new Observable<Category>();
        }

        return this.http.get<Category>(this.urlBuilder.getFullUrl());
    }

}