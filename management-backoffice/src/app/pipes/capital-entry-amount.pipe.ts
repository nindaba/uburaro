import {Pipe, PipeTransform} from "@angular/core";
import {CapitalEntry, PaymentModeType} from "../model/navigation.model";

@Pipe({
    name: "getEntryAmount",
    standalone: true
})
export class CapitalEntryAmountPipe implements PipeTransform {
    transform(value: CapitalEntry): number {
        if(value.description.startsWith(PaymentModeType.DEBT)){
            return -value.amount;
        }
        return value.amount;
    }
}