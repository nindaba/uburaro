import {Component, Input} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {FormGroup, ReactiveFormsModule} from "@angular/forms";
import {NgClass, NgForOf, NgIf} from "@angular/common";

@Component({
    selector: 'mb-pagination',
    templateUrl: './pagination.component.html',
    imports: [
        TranslateModule,
        ReactiveFormsModule,
        NgIf,
        NgForOf,
        NgClass
    ],
    standalone: true
})
export class PaginationComponent {
    @Input()
    pageForm: FormGroup = new FormGroup({});
    @Input()
    pages:number = 1;
    getLeadingPages() : number[]{
        if(this.pages > 3){
            let currentPage = this.getCurrentPage();
            return [...Array(this.pages).keys()].slice(currentPage > 2 ? currentPage -2 : 1, currentPage +3);
        }
        return [...Array(this.pages).keys()].slice(1);
    }

    getCurrentPage() {
        return this.pageForm.get('currentPage')?.value;
    }
    setCurrentPage(page:number){
        this.pageForm.get('currentPage')?.setValue(page);
    }
}
