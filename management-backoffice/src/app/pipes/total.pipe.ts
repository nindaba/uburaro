import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name:"calcTotal",
    standalone: true
})
export class TotalPipe implements PipeTransform{
    transform(values: any[], attribute:string): any {
        return values.reduce((a,b)=> a + b[attribute],0);
    }
}