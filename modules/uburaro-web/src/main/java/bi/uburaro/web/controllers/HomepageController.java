package bi.uburaro.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static bi.uburaro.web.UburaroWebConstants.Controller.homepage;

@Controller
public class HomepageController {
    @RequestMapping("/")
    public String getHomepage(){
        return homepage;
    }
}
