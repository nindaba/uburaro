import {Component} from '@angular/core';
import {TopNavService} from "./top-nav.service";
import {Router} from "@angular/router";

@Component({
    selector: 'mb-top-nav',
    templateUrl: './top-nav.component.html'
})
export class TopNavComponent {
    constructor(private service: TopNavService,private router: Router) {
    }

    getName(nodeId: string) {
        return this.service.getName(nodeId);
    }

    isEnabled(nodeId: string) {
        return this.service.isEnabled(nodeId);
    }

    onSearch() {
        this.router.navigate(["reports"]);
    }
}
