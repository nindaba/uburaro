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
            String category = endpoint+"/{code}";
        }

        interface DataImport{
            String endpoint = "data-import";
        }

    }
}
