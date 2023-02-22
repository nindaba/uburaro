import {Component, Input} from '@angular/core';
import {NavNode} from "../../../model/navigation.model";
import {SideNavService} from "./side-nav.service";
import {Router} from "@angular/router";
import {BreadcrumbsService} from "../top-nav/breadcrumbs.service";

@Component({
    selector: 'mb-nav-node',
    templateUrl: './nav-node.component.html'
})
export class NavNodeComponent{

    @Input() node: NavNode = {
        icon: "home",
        name: "nodes.name.home"
    };

    public constructor(private service: SideNavService, private router: Router, private breadService: BreadcrumbsService) {
    }

    getActiveClass() {
        return this.node.active ? "active" : "";
    }

    onClick() {
        if (!this.breadService.isFacilitySelected()) {
            this.breadService.toggleFacilitySelector();
        }
        this.service.setActiveNode(this.node);
        this.router.navigate([this.node.routeId])
    }
}
