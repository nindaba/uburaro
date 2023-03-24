import {Observable} from "rxjs";
import {Invoice, Page, Pageable, RentContract} from "../../model/navigation.model";

export abstract class ContractService{

    /**
     * Sends the form to the backed for contract registration
     *
     * @param contract
     */
    abstract createContract(contract: RentContract): Observable<any>;

    /**
     * Delete Rent Contract
     *
     * @Param code
     */
    abstract deleteContract(codes: string[]): Observable<any>;


    /**
     * Checks whether the contract is included in the selected contracts
     *
     * @param contract
     */
    abstract isSelected(contract?:RentContract): boolean;

    /**
     * add the contract to the selected contract list,<br>
     * If the contract is already added, then it will be removed
     *
     * @param contract
     */
    abstract updateSelection(contract?:RentContract):void;

    /**
     * Gets all the selected contracts
     *
     * @return contracts
     */
    abstract getSelected(): RentContract[];

    /**
     * Resets Contract Selection
     */
    abstract resetSelection():void;
}