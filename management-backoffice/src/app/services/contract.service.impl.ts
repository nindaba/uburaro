import {ContractService} from "../components/rent/contract.service";
import {RentContract} from "../model/navigation.model";
import {Observable} from "rxjs";
import {UrlBuilderService} from "../utils/UrlBuilder.service";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class ContractServiceImpl implements ContractService {
    constructor(protected urlBuilder: UrlBuilderService, protected http: HttpClient) {
    }

    createContract(contract: RentContract): Observable<any> {
        let url = this.getRentContractUrl();
        return this.http.patch(url, contract);
    }

    private getRentContractUrl() {
        let url = this.urlBuilder.getUrlForEndPoint("rentContract");
        return url.replaceAll(/{|}/g,"").replaceAll("}","");
    }

    deleteContract(codes: string[]): Observable<any> {
        return this.http.delete(this.getRentContractUrl(), {params: {codes: codes}});
    }
}