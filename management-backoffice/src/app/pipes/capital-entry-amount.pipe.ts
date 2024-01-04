import {Pipe, PipeTransform} from "@angular/core";
import {CapitalEntry, PaymentModeType} from "../model/navigation.model";

@Pipe({
    name: "getEntryAmount",
    standalone: true
})
export class CapitalEntryAmountPipe implements PipeTransform {
    transform(value: CapitalEntry): number {
        return value.description?.startsWith(PaymentModeType.DEBT) ? -value.amount: value.amount;
    }
}