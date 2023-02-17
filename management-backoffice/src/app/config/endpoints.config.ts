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

    relation: {
        facilities: "facility",
        categories: "category",
        inventories: "inventory",
        clients: "client"
    }
}