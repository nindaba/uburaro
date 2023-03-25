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
    invoices: string = "";
    clientInvoices: string = "";
    rents: string = "";
    rent: string = "";
    inventoryOrders: string = "";
    inventoryOrder: string = "";
    inventoryFacilityOrders: string = "";
    inventoryClientOrders: string = "";
    clientOrders: string = "";
    contractOrders: string = "";
    clientsReport: string = "";
    rentFacilityOrders:string = "";
    relation: { [key: string]: string } = {};
    contracts: string = "";

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
    code: string | UnitType | InventoryOrderType;
    name: string | UnitType | InventoryOrderType;
}

export enum CapitalType {
    INTERNAL = "INTERNAL", EXTERNAL = "EXTERNAL", EXPENSE = "EXPENSE"
}

export enum InventoryOrderType {
    REFILL = "REFILL",
    SOLD = "SOLD",
    OUT = "OUT"
}

export enum PaymentModeType {
    CASH = "CASH", BANK = "BANK", DEBT = "DEBT"
}

export interface Order extends Item {
    orderNumber?: string;
    quantity: number;
    unit?: string;
    orderDate?: Date;
    cost: number;
    itemName?: string;
    itemCode?: string;
    clientName?: string;
    clientCode?: string;
    paid?: boolean;
}

export interface InventoryOrder extends Order {
    orderEntry: InventoryOrderType;
}

export interface CapitalEntry {
    amount: number;
    entryType: CapitalType;
    dateCreated: Date;
    description: string;
}

export interface Capital {
    currentValue: number;
    entries?: CapitalEntry[]
}

export interface RentOrder extends Order {
    from: Date;
    to: Date;
}

export interface InventoryOrder extends Item {
}

export interface Invoice extends Item {
    client?: CodeName;
    orders?: Order[];
    amount?: number;
    invoiceNumber?: string;
    paymentMode?: PaymentModeType;
    description?: string;
}

export interface Client extends Item {
    totalDebt: number;
    name?: string;
    address?: string;
    orders?: Order[];
    invoices?: Invoice[];
    contracts?: RentContract[];
}

export interface Inventory extends Item {
    name: string;
    quantity: number;
    cost: number;
    category: Category;
    unit: string;
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

export interface Rent extends Item {
    facility?: Facility;
    name?: string;
    unit?: UnitType;
    cost?: number;
    address?: string;
    contracts?: RentContract[];
    currentContract?: RentContract;
}

interface Name {
    name?: string | UnitType | InventoryOrderType;
}

export interface RentContract extends Item, Name {
    from: Date;
    to: Date;
    costPerUnit: number;
    unit: UnitType;
    contractFileName: string;
    property?: Rent;
    client?: Client;
    orders?: RentOrder[];
    nextOrderDate: Date;
    hasOrders: boolean;
}

export enum UnitType {
    DAYS = 1,
    MONTHS = 30,
    YEARS = 365,
}

export enum NotificationStatus {
    ERROR = "fail", SUCCESS = "success", NONE = ""
}

export interface MBNotification {
    status: NotificationStatus,
    message?: string;
}

export interface DateRange {
    from: Date,
    to: Date
}

export interface ClientReport {
    clients: Client[];
    invoicesPage: Page<Invoice>;
}

export interface Pageable{
    pageSize:number;
    currentPage:number;
    sort:string;
    sortOrder:string;
}

export interface Page<ITEM>{
    content: ITEM[];
    pages:number;
}
