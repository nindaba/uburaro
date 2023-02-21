package bi.manager.web;

public interface ManagerWebConstants {
    interface Controller {

        String allFields = "allFields";

        interface Facility {
            String endpoint = "facilities";
            String facility = endpoint + "/{code}";
            String capital = facility + "/capital";
        }

        interface Category {
            String endpoint = Facility.facility + "/categories";
        }

        interface Client {
            String endpoint = Facility.facility + "/clients";
            String client = "/{clientCode}";
        }

        interface Rent {
            String endpoint = Facility.facility + "/rents";
            String rent = "/{rentCode}";
        }

        interface Inventory {
            String endpoint = Facility.facility + "/inventories";
            String categoryInventories = Category.endpoint + "/{categoryCode}/inventories";
            String inventory = endpoint + "/{inventoryCode}";
        }

        interface Orders {
            String endpoint = "orders";

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
            }

        }

        interface DataImport {
            String endpoint = "data-import";
        }

    }
}
