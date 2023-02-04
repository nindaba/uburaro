import {Component, Input} from '@angular/core';
import {NavNode} from "../../../model/navigation.model";
import {SideNavService} from "./side-nav.service";

@Component({
    selector: 'mb-nav-node',
    templateUrl: './nav-node.component.html'
})
export class NavNodeComponent {

    @Input() node:NavNode ={
        icon:"home",
        name:"nodes.name.home"
    };
    public constructor(private service: SideNavService) {
    }

    getActiveClass() {
        return this.node.active ? "active" : "";
    }

    onClick() {
        this.service.setActiveNode(this.node);
    }
}
