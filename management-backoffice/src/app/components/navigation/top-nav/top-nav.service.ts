import NavigationConfig from "../../../../assets/content-config/navigation.json";
import {NavNode} from "../../../model/navigation.model";

export class TopNavService {
    nodes: NavNode[] = NavigationConfig["top-nav"].nodes;

    activeNode: NavNode = {};

    setActiveNode(node: NavNode) {
        this.activeNode.active = false;
        node.active = true;
        this.activeNode = node;
    }

    getNode(nodeId: string): NavNode {
        return this.nodes.find(node => node.id === nodeId) || {};
    }

    isEnabled(nodeId: string): boolean {
        //todo: report to be replaced with route
        return !(nodeId =="discard" || nodeId == "save" || this.getNode(nodeId).disabledOn?.includes("reports"));
    }
    getName(nodeId: string): string{
        return this.getNode(nodeId).name || nodeId;
    }
}