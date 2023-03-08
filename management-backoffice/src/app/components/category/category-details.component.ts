import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, tap} from "rxjs";
import {Category} from "../../model/navigation.model";
import {FormBuilder, FormControl} from "@angular/forms";
import {NEW_ITEM} from "../navigation/navigation.constants";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {TopNavService} from "../navigation/top-nav/top-nav.service";
import {AbstractDetailsComponent} from "../abstract-details.component";
import DetailsConfig from "../../../assets/content-config/details-page.json"
import {MBItemService} from "../../services/MBItem.service";
import {Router} from "@angular/router";

@Component({
    selector: 'mb-category-details',
    templateUrl: './category-details.component.html'
})
export class CategoryDetailsComponent extends AbstractDetailsComponent implements OnInit {
    $category: Observable<Category> = new Observable();
    inventoryHeads: string[] = DetailsConfig.category.inventory.heads;

    constructor(private formBuilder: FormBuilder,
                private itemService: MBItemService,
                private breadService: BreadcrumbsService,
                protected override topNavService: TopNavService,
                protected override router: Router
    ) {
        super(topNavService, router);
    }

    private createFrom(code: string = "", name: string = "") {
        return this.formBuilder.group({
            code: new FormControl({value: code, disabled: !!code}),
            name: [name]
        });
    }

    ngOnInit(): void {
        this.itemForm = this.createFrom();
        let code = this.breadService.pages.details;

        if (code && code !== NEW_ITEM) {
            this.$category = this.itemService.getItemByCode<Category>(code, true).pipe(
                tap(facility => {
                    let {code, name} = facility;
                    this.itemForm = this.createFrom(code, name);
                    this.subscribeToForm();
                }),
            );
            this.subscribeToDelete(this.breadService.pages.page);
        } else {
            this.subscribeToForm();
        }
    }
}
