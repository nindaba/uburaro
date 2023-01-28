package bi.uburaro.web.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static bi.uburaro.web.UburaroWebConstants.Controller.Auth.login;

@RestController
public class AuthController {
    @RequestMapping(path = login,method = RequestMethod.POST)
    public String login(@RequestBody String body){
        return "body";
    }
}
