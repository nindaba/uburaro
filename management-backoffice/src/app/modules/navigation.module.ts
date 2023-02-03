export interface NavNode {
    icon?: string;
    name?: string;
    route?: string;
    active?: boolean;
}

export interface ChildNavNode {
    title: string,
    nodes: NavNode[]
}
