import {EndpointConfig} from "../model/navigation.model";

export const endpointsConfig: EndpointConfig= {
    baseUrl: "http://localhost:8080/manager-api/",
    allFields: {allFields: true},
    facilities: "facilities",
    facility: "facilities/{id}"
}