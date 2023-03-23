package bi.manager.web;

public interface ManagerWebConstants {
    interface Controller {

        String allFields = "allFields";
        String report = "/report";

        interface Facility {
            String endpoint = "facilities";
            String facility = endpoint + "/{code}";
            String facilityCode =  "/{code}";
            String capital = facility + "/capital";
        }

        interface Category {
            String endpoint = Facility.facility + "/categories";
        }

        interface Client {
            String endpoint = Facility.facility + "/clients";
            String client = "/{clientCode}";
            String rentContract = client + "/rent-contract";

            interface Invoice {
                String facilityInvoices = Facility.facility + "/invoices";
                String clientInvoices = Client.endpoint + Client.client + "/invoices";
            }
        }

        interface Rent {
            String endpoint = Facility.facility + "/rents";
            String rent = "/{rentCode}";
            String rentContract = rent + "/rent-contract";
            String contracts = "/contracts";
        }

        interface Inventory {
            String endpoint = Facility.facility + "/inventories";
            String categoryInventories = Category.endpoint + "/{categoryCode}/inventories";
            String inventory = endpoint + "/{inventoryCode}";
        }

        interface Orders {
            String endpoint = "orders";
            String clientOrders = Client.client+"/"+endpoint;

            interface Inventory {
                String endpoint = Orders.endpoint + "/inventory";
                String inventoryOrders = "/{code}";
                String facilityOrders = "/facility/{code}";
                String clientOrders = "/client/{code}";
            }

            interface Rent {
                String endpoint = Orders.endpoint + "/rent";
                String inventoryOrders = "/{code}";
                String facilityOrders = "/facility/{code}";
                String clientOrders = "/client/{code}";
                String contractOrders = "/contract/{code}";

            }

        }

        interface DataImport {
            String endpoint = "data-import";
        }

    }
}
