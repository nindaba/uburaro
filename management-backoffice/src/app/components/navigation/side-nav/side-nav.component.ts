import {Component, Input, OnInit} from '@angular/core';
import {SideNavService} from "./side-nav.service";
import {ChildNavNode, NavNode} from "../../../model/navigation.model";

@Component({
    selector: 'mb-side-nav',
    templateUrl: './side-nav.component.html'
})
export class SideNavComponent implements OnInit{
    mainNodes: NavNode[] = this.service.mainNodes;
    childNodes: ChildNavNode = this.service.childNodes;

    public constructor(private service: SideNavService) {
    }

    ngOnInit(): void {
        let navNode = this.mainNodes.find(node => node.active) || {};
        this.service.setActiveNode(navNode);
    }
}
