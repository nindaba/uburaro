import NavigationConfig from "../../../../assets/content-config/navigation.json";
import {NavNode} from "../../../model/navigation.model";
import {Injectable} from "@angular/core";
import {ActivatedRoute, ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
@Injectable({providedIn:"root"})
export class TopNavService {
    nodes: NavNode[] = NavigationConfig["top-nav"].nodes;

    activeNode: NavNode = {};

    constructor(private router: Router) {
    }
    setActiveNode(node: NavNode) {
        this.activeNode.active = false;
        node.active = true;
        this.activeNode = node;
    }

    getNode(nodeId: string): NavNode {
        return this.nodes.find(node => node.id === nodeId) || {};
    }

    isEnabled(nodeId: string): boolean {
        let path = this.router.url.split("?")[0].split("/")[1];
        console.log()
        return !(nodeId =="discard" || nodeId == "save" || this.getNode(nodeId).disabledOn?.includes(path));
    }
    getName(nodeId: string): string{
        return this.getNode(nodeId).name || nodeId;
    }
}