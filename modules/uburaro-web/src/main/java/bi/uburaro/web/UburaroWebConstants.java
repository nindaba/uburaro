package bi.uburaro.web;

public interface UburaroWebConstants {
    interface Controller {
        String homepage = "index.html";
        String allFields = "allFields";


        interface Hotel {
            String endpoint = "hotels";
            String hotelCode = "hotelCode";
            String hotel = endpoint + "/{" + hotelCode + "}";
            String branches = hotel + "/branches";
            String branchCode = "branchCode";
            String branch = hotel + "/branches/{" + branchCode + "}";
            String importCurrent = endpoint+"/import/current";
        }

        interface Employee {
            String endpoint = "employees";
            String employeeCode = "employeeCode";
            String hotelEmployees = Hotel.hotel + "/" + endpoint;
            String employee = endpoint + "/{" + employeeCode + "}";
        }

        interface Customer {
            String endpoint = "customers";
            String customerCode = "customerCode";
            String hotelCustomers = Hotel.hotel + endpoint;
            String customer = endpoint + "/{" + customerCode + "}";
        }

        interface Company {
            String companyCode = "companyCode";
            String companyCodes = "companyCodes";

        }

        interface EmployeeGroup {
            String groupCode = "groupCode";
            String groupCodes = "groupCodes";
        }
        interface Auth{
            String endpoint = "auth";
            String login = endpoint+"/login";
            String password = "password";
            String username = "username";
        }
    }
}
