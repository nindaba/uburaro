import {Injectable} from "@angular/core";
import {EndpointConfig} from "../../../model/navigation.model";
import {BehaviorSubject} from "rxjs";

@Injectable({
    providedIn: "root"
})
export class BreadcrumbsService {
    public readonly CHOSE_FACILITY = "facility.selector.title";
    facility: string = this.CHOSE_FACILITY
    pages: { page?: string; details?: string; } = {};
    $facility: BehaviorSubject<string> = new BehaviorSubject<string>(this.facility);

    constructor(private endpoint: EndpointConfig) {
    }

    isFacilityInUse(): boolean {
        return this.facility == this.pages.details && this.pages.page == this.endpoint.facilities;
    }

    setFacility() {
        if (this.pages.page == this.endpoint.facilities) {
            this.facility = (this.pages.details != this.facility ? this.pages.details : this.CHOSE_FACILITY) || this.CHOSE_FACILITY;
        }
        this.$facility.next(this.facility);
    }

    isFacilitySelected(): boolean{
        return this.CHOSE_FACILITY != this.facility;
    }
}