import {Observable} from "rxjs";
import {Item} from "../model/navigation.model";
import {Injectable} from "@angular/core";
import {MBItemServiceImpl} from "./MBItem.service.impl";


export abstract class MBItemService{
    /**
     * Gets all the Items that belong to the selected facility which are active from the server
     *
     * @return observable of Item
     */
    abstract  getItemByFacilityCode<ITEM extends Item[]>() : Observable<ITEM>;

    /**
     * Gets an Item with {@code code} from the server
     *
     * @param code
     * @param allFields
     * @return item
     */
    abstract getItemByCode<ITEM extends Item>(code: string,allFields:boolean): Observable<ITEM>;

}
