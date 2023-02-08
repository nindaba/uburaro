import {Component} from '@angular/core';
import {TopNavService} from "./top-nav.service";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "./breadcrumbs.service";

@Component({
    selector: 'mb-top-nav',
    templateUrl: './top-nav.component.html'
})
export class TopNavComponent {
    constructor(private service: TopNavService,private router: Router,private breadcrumbsService: BreadcrumbsService) {
    }

    getName(nodeId: string) {
        return this.service.getName(nodeId);
    }

    isEnabled(nodeId: string) {
        return this.service.isEnabled(nodeId);
    }

    onSearch() {
        this.router.navigate(["clients","tou"],{queryParams:{facility:this.breadcrumbsService.facility}});
    }

    delete() {
        this.service.$delete.emit();
    }
}
