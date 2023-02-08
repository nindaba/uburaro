export interface NavNode {
    id?:string,
    icon?: string;
    name?: string;
    routeId?: string;
    active?: boolean;
    disabledOn?:string[];
}
export interface EndpointConfig{
    [key:string]: string;
}
export interface ChildNavNode {
    title: string,
    nodes: NavNode[]
}

interface Capital {

}

interface Client {
}

interface Category {

}

export interface Facility{
    code: string;
    name?:string;
    alias?:string;
    dateCreated?:Date;
    capital?:Capital;
    clients?:Client;
    categories?:Category;
}
