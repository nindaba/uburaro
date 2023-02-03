import NavigationConfig from "../../../../assets/content-config/navigation.json";
import {ChildNavNode, NavNode} from "../../../modules/navigation.module";

export class SideNavService{
    mainNodes: NavNode[] = NavigationConfig["side-nav"]["main-nodes"].nodes;
    childNodes: ChildNavNode = NavigationConfig["side-nav"]["child-nodes"];

    activeNode: NavNode = {};
    setActiveNode(node: NavNode) {
        this.activeNode.active = false;
        node.active = true;
        this.activeNode = node;
    }
}