export interface NavNode {
    id?:string,
    icon?: string;
    name?: string;
    routeId?: string;
    active?: boolean;
    disabledOn?:string[];
}
export class EndpointConfig{
    facilities:string ="";
    facility:string ="";
    baseUrl:string ="";
    allFields: any;

    [key:string]: any;
    constructor(){}
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
    address?:string;
    dateCreated?:Date;
    capital?:Capital;
    clients?:Client[];
    categories?:Category[];
}
