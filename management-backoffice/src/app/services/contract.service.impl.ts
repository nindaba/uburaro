import {ContractService} from "../components/rent/contract.service";
import {RentContract} from "../model/navigation.model";
import {Observable} from "rxjs";
import {UrlBuilderService} from "../utils/UrlBuilder.service";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class ContractServiceImpl implements ContractService {
    selectedContracts:RentContract[] = [];
    constructor(protected urlBuilder: UrlBuilderService, protected http: HttpClient) {
    }

    createContract(contract: RentContract): Observable<any> {
        let url = this.getRentContractUrl();
        return this.http.patch(url, contract);
    }

    private getRentContractUrl() {
        let url = this.urlBuilder.getUrlForEndPoint("rentContract");
        return url.replaceAll(/{|}/g,"");
    }

    deleteContract(codes: string[]): Observable<any> {
        return this.http.delete(this.getRentContractUrl(), {params: {codes: codes}});
    }

    getSelected(): RentContract[] {
        return this.selectedContracts;
    }

    isSelected(contract?: RentContract): boolean {
        if(contract){
            return this.selectedContracts.includes(contract);
        }
        return false;
    }

    resetSelection(): void {
        this.selectedContracts.splice(0);
    }

    updateSelection(contract?: RentContract): void {
        if(contract){
            let index = this.selectedContracts.indexOf(contract);
            if(index >=0){
                this.selectedContracts.splice(index,1);
            }
            else{
                this.selectedContracts.push(contract);
            }
        }

    }

}