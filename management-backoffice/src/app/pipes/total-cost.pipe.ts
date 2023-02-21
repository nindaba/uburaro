import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name:"calcTotalCost",
    standalone: true
})
export class TotalCostPipe implements PipeTransform{
    transform(values: any[]): any {
        return values.reduce((a,b)=> a + b.cost* b.quantity,0);
    }
}