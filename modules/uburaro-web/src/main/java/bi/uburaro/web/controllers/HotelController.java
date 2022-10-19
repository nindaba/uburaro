package bi.uburaro.web.controllers;

import bi.uburaro.facade.data.HotelData;
import bi.uburaro.facade.facades.HotelFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

import static bi.uburaro.web.UburaroWebConstants.Controller.Hotel.*;

@Log4j2
@RestController
public class HotelController {
    @Resource(name = "hotelFacade")
    private HotelFacade hotelFacade;

    @RequestMapping(path = hotel, method = RequestMethod.GET)
    public ResponseEntity<HotelData> getHotel(@PathVariable(name = hotelCode) String code,
                                              @RequestParam(name = "allFields", required = false) boolean allFields) {
        return ResponseEntity.ok(hotelFacade.getHotelByCode(code,allFields));
    }

    @RequestMapping(path = branches, method = RequestMethod.GET)
    public ResponseEntity<Collection<HotelData>> getHotelBranches(@PathVariable(name = hotelCode) String code) {
        return ResponseEntity.ok(hotelFacade.getHotelBranches(code));
    }

    @RequestMapping(path = branch, method = RequestMethod.GET)
    public ResponseEntity<HotelData> getCurrentHotelBranch(@PathVariable(name = branchCode) String code) {
        return ResponseEntity.ok(hotelFacade.getCurrentHotelBranch(code));
    }

    @RequestMapping(path = endpoint, method = RequestMethod.GET)
    public ResponseEntity<Collection<HotelData>> getAllHotels() {
        return ResponseEntity.ok(hotelFacade.getAllHotels());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createHotel(@RequestBody HotelData hotelData) {
        hotelFacade.createHotel(hotelData);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = hotel, method = RequestMethod.PATCH)
    public ResponseEntity<HotelData> updateHotel(@RequestBody HotelData hotelData) {
        return ResponseEntity.ok(hotelFacade.updateHotel(hotelData));
    }

    @RequestMapping(path = hotel, method = RequestMethod.DELETE)
    public ResponseEntity<Void> createHotel(@PathVariable(name = hotelCode) String code) {
        hotelFacade.deleteHotel(code);
        return ResponseEntity.ok().build();
    }


}
