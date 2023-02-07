package bi.manager.web;

public interface ManagerWebConstants {
    interface Controller {

        String allFields = "allFields";

        interface Facility {
            String endpoint = "facilities";
            String facility = endpoint+"{facilityId}";
        }

        interface DataImport{
            String endpoint = "data-import";
        }

    }
}
