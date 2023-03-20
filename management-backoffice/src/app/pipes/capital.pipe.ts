import {Pipe, PipeTransform} from "@angular/core";
import {CapitalEntry, PaymentModeType} from "../model/navigation.model";

@Pipe({
    name: "calcCapital",
    standalone: true
})
export class TotalPipe implements PipeTransform {
    transform(values: CapitalEntry[]): number {
        return values.reduce((a, b) => a + (b.description?.startsWith(PaymentModeType.DEBT) ? -b.amount : b.amount), 0);
    }
}