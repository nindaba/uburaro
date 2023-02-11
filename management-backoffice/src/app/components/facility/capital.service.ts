import {Injectable} from "@angular/core";
import {Capital, CapitalEntry, CapitalType} from "../../model/navigation.model";
import {Observable} from "rxjs";

export abstract class CapitalService {

    /**
     * Adds a capital to the facility
     *
     * @param value
     * @param type
     * @return void observable
     */
    abstract addCapital(value: number, type: CapitalType): Observable<void>;

    abstract getCapital():Observable<Capital>;

    /**
     * Get Entries of capital which belongs to a {@code facilityCode} with date that is in the given range
     *
     * @param from Date
     * @param to Date
     * @return observable of entries
     */
    abstract getCapitalEntries(from: Date, to: Date): Observable<CapitalEntry[]>
}