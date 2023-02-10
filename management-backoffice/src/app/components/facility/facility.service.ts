import {Facility} from "../../model/navigation.model";
import {Observable} from "rxjs";

export abstract class FacilityService{

    /**
     * Fetch a facility by {@code code}
     *
     * @param code
     * @return facility, and if it is not found null will be returned
     */
    abstract getFacilityByCode(code: string ):Observable<Facility>;

    /**
     * Fetches all the facilities
     *
     * @return an array of facilities
     */
    abstract getAllFacilities():Observable<Facility[]>;

    /**
     * Disables all the facilities related with the ids provided
     * @param facilityIds
     */
    abstract deleteFacilities(facilities: Facility[] ):Observable<any>;

    /**
     * Creates a facility from the server
     *
     * @param facility
     * @return facility
     */
    abstract createFacility(facility: Facility ) : void;

    /**
     * updates the facility
     *
     * @param facility
     */
    abstract updateFacility(facility: Facility): void;

    /**
     * Fetch a facility by {@code code} with all fields populated
     *
     * @param code
     * @param allFields
     * @return facility, and if it is not found null will be returned
     */
    abstract getFullFacilityByCode(code: string): Observable<Facility>;
}