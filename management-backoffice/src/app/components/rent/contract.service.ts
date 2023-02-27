import {Observable} from "rxjs";
import {RentContract} from "../../model/navigation.model";

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
}