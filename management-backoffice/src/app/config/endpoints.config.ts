import {EndpointConfig} from "../model/navigation.model";

export const endpointsConfig: EndpointConfig = {
    baseUrl: "http://localhost:8080/manager-api/",
    allFields: {allFields: true},
    facilities: "facilities",
    facility: "facilities/{code}",
    capital: "facilities/{code}/capital",
    capitalPdf: "facilities/{code}/capital/pdf",
    capitalEntries: "facilities/{code}/capital/entries",
    capitalSummary: "facilities/{code}/capital-summary",
    categories: "facilities/{code}/categories",
    category: "facilities/{code}/categories/{category-code}",
    inventories: "facilities/{code}/inventories",
    inventoriesPdf: "orders/inventory/facility/{code}/pdf",
    inventory: "facilities/{code}/inventories/{inventory-code}",
    clients: "facilities/{code}/clients",
    clientsPdf: "facilities/{code}/clients/pdf",
    client: "facilities/{code}/clients/{client-code}",
    clientInvoices: "facilities/{code}/clients/{client-code}/invoices",
    clientRentContract: "facilities/{code}/clients/{client-code}/rent-contract",
    clientOrders: "facilities/{code}/clients/{client-code}/orders",
    clientsReport: "facilities/{code}/clients/report",
    rents: "facilities/{code}/rents",
    rent: "facilities/{code}/rents/{rent-code}",
    rentContract: "facilities/{code}/rents/{rent-code}/rent-contract",
    rentsPdf: "orders/rent/facility/{code}/pdf",
    contracts: "facilities/{code}/rents/contracts",
    invoices: "facilities/{code}/invoices",

    /**
     * note there is no endpoint for getting a specific order
     */
    inventoryOrder: "orders/inventory",
    inventoryOrders: "orders/inventory/{code}",
    inventoryClientOrders: "orders/inventory/client/{code}",
    inventoryFacilityOrders: "orders/inventory/facility/{code}",
    rentFacilityOrders: "orders/rent/facility/{code}",
    contractOrders: "orders/rent/contract/{code}",

    relation: {
        facilities: "facility",
        categories: "category",
        inventories: "inventory",
        clients: "client",
        rents: "rent"
    }
}