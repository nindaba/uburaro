import {EndpointConfig} from "../model/navigation.model";

export const endpointsConfig: EndpointConfig = {
    baseUrl: "http://localhost:8080/manager-api/",
    allFields: {allFields: true},
    facilities: "facilities",
    facility: "facilities/{code}",
    capital: "facilities/{code}/capital",
    capitalEntries: "facilities/{code}/capital/entries",
    categories: "facilities/{code}/categories",
    category: "facilities/{code}/categories/{category-code}",
    inventories: "facilities/{code}/inventories",
    inventory: "facilities/{code}/inventories/{inventory-code}",
    clients: "facilities/{code}/clients",
    client: "facilities/{code}/clients/{client-code}",
    clientInvoices: "facilities/{code}/clients/{client-code}/invoices",
    clientRentContract: "facilities/{code}/clients/{client-code}/rent-contract",
    clientOrders: "facilities/{code}/clients/{client-code}/orders",
    rents: "facilities/{code}/rents",
    rent: "facilities/{code}/rents/{rent-code}",
    rentContract: "facilities/{code}/rents/{rent-code}/rent-contract",
    invoices: "facilities/{code}/invoices",

    /**
     * note there is no endpoint for getting a specific order
     */
    inventoryOrder: "orders/inventory",
    inventoryOrders: "orders/inventory/{code}",
    inventoryClientOrders: "orders/inventory/client/{code}",
    inventoryFacilityOrders: "orders/facility/{code}",
    contractOrders: "orders/rent/contract/{code}",

    relation: {
        facilities: "facility",
        categories: "category",
        inventories: "inventory",
        clients: "client",
        rents: "rent"
    }
}