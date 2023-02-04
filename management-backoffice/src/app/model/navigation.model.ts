export interface NavNode {
    id?:string,
    icon?: string;
    name?: string;
    routeId?: string;
    active?: boolean;
    disabledOn?:string[];
}

export interface ChildNavNode {
    title: string,
    nodes: NavNode[]
}
