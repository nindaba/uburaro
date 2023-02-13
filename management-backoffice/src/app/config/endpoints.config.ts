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
    relation: {
        facilities: "facility",
        categories: "category"
    }
}