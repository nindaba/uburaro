package bi.manager.web;

public interface ManagerWebConstants {
    interface Controller {

        String allFields = "allFields";

        interface Facility {
            String endpoint = "facilities";
            String facility = endpoint+"/{code}";
            String capital = facility+"/capital";
        }
        interface Category {
            String endpoint = Facility.facility+"/categories";
        }
        interface Inventory {
            String endpoint = Facility.facility+"/inventories";
            String categoryInventories =  Category.endpoint+"/{categoryCode}/inventories";
            String inventory = endpoint+"/{inventoryCode}";
        }

        interface DataImport{
            String endpoint = "data-import";
        }

    }
}
