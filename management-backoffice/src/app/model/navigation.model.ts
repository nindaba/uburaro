import {BehaviorSubject} from "rxjs";

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
    inventories: string = "";
    inventory: string = "";
    clients: string = "";
    client: string = "";
    inventoryOrders: string = "";
    inventoryOrder: string = "";
    inventoryFacilityOrders: string = "";
    inventoryClientOrders: string = "";

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

export interface CodeName {
    code: string,
    name: string
}

export enum CapitalType {
    INTERNAL = "INTERNAL", EXTERNAL = "EXTERNAL", EXPENSE = "EXPENSE"
}

export enum InventoryOrderType {
    REFILL = "REFILL",
    SOLD = "SOLD",
    OUT = "OUT"
}

export interface Order {
    orderNumber?: string;
    quantity: number;
    unit?: string;
    orderDate?: Date;
    cost: number;
    itemName?: string;
    itemCode?: string;
    clientName?: string;
    clientCode?: string;
}

export interface InventoryOrder extends Order {
    orderEntry: InventoryOrderType;
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

export interface RentOrder extends Item {
}

export interface InventoryOrder extends Item {
}

export interface Invoice extends Item {
}

export interface Client extends Item {
    totalDebt: number;
    name?: string;
    address?: string;
    rentOrders?: RentOrder[];
    inventoryOrders?: InventoryOrder[];
    invoices?: Invoice[];
}

export interface Inventory extends Item {
    name: string;
    quantity: number;
    cost: number;
    category: Category;
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
