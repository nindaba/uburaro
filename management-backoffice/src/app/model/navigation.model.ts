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

    [key: string]: any;

    constructor() {
    }
}

export interface ChildNavNode {
    title: string,
    nodes: NavNode[]
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

export interface Client {
}

export interface Category {

}

export interface Facility {
    code: string;
    name?: string;
    alias?: string;
    address?: string;
    dateCreated?: Date;
    capital?: Capital;
    clients?: Client[];
    categories?: Category[];
}
