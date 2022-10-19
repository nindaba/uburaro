package bi.uburaro.web;

public interface UburaroWebConstants {
    interface Controller {
        String homepage = "backoffice/index.html";
        interface Hotel {
            String endpoint = "hotels";
            String hotelCode = "hotelCode";
            String hotel = endpoint+"/{"+ hotelCode +"}";
            String branches = hotel+"/branches";
            String branchCode = "branchCode";
            String branch = hotel+"/branches/{"+ branchCode +"}";
        }
    }
}
