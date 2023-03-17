import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";
import {DateRange} from "../model/navigation.model";

export const dateRangeValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
    let dateRange: DateRange = control.getRawValue();
    let valid = new Date(dateRange.from).getTime() < new Date(dateRange.to).getTime();
    return valid ? null : {invalidRange: true};
}