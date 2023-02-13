export interface NavNode {
    id?: string,
    icon?: string;
    name?: string;
    routeId?: string;
    active?: boolean;
    disabledOn?: string[];
}

export class EndpointConfig {
    facilities: string = "";
    facility: string = "";
    baseUrl: string = "";
    allFields: any;
    capitalEntries: string = "";
    capital: string = "";
    categories: string = "";
    category: string = "";
    relation: { [key: string]: string } = {};


    [key: string]: any;

    constructor() {
    }
}

export interface ChildNavNode {
    title: string,
    nodes: NavNode[]
}

export interface Item {
    code: string;
    dateCreated?: Date;
}

export enum CapitalType {
    INTERNAL = "INTERNAL", EXTERNAL = "EXTERNAL", EXPENSE = "EXPENSE"
}

export interface CapitalEntry {
    amount: number;
    entryType: CapitalType;
    dateCreated: Date;
}

export interface Capital {
    currentValue: number;
    entries?: CapitalEntry[]
}

export interface Client extends Item {

}

interface Inventory extends Item {
}

export interface Category extends Item {
    name?: string;
    facility?: Facility;
    inventories?: Inventory[];
}

export interface Facility extends Item {

    name?: string;
    alias?: string;
    address?: string;
    capital?: Capital;
    clients?: Client[];
    categories?: Category[];
}
