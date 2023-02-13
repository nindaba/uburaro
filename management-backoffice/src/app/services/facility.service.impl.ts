import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {FacilityService} from "../components/facility/facility.service";
import {EndpointConfig, Facility} from "../model/navigation.model";
import {Observable} from "rxjs";
import {UrlBuilderService} from "../utils/UrlBuilder.service";
import {TopNavService} from "../components/navigation/top-nav/top-nav.service";

@Injectable()
export class FacilityServiceImpl implements FacilityService {
    constructor(
        private http: HttpClient,
        private urlBuilder: UrlBuilderService,
        private topService: TopNavService,
        private endpointConfig: EndpointConfig) {
    }

    createFacility(facility: Facility): void {
    }

    deleteFacilities(facilities: Facility[]): Observable<any> {
        return this.http.delete(
            this.urlBuilder.getUrl(this.endpointConfig.facilities),
            {params: {"facilityIds": facilities.map(facility => facility.code).join(",")}}
        )
    }

    getAllFacilities(): Observable<Facility[]> {
        let url: string = this.urlBuilder.getUrl(this.endpointConfig.facilities);
        return this.http.get<Facility[]>(url);
    }

    getFacilityByCode(code: string): Observable<Facility> {
        let url: string = this.urlBuilder.getUrl("facility", [{key: "code", value: code}]);
        return this.http.get<Facility>(url);
    }

    updateFacility(facility: Facility): void {
    }

    getFullFacilityByCode(code: string): Observable<Facility> {
        let url: string = this.urlBuilder.getUrl("facility", [{key: "code", value: code}]);
        return this.http.get<Facility>(url, {params: this.endpointConfig.allFields});
    }


}