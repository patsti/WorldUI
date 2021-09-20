package patrik.worldui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import patrik.restapi.objects.ResponseObject;
import patrik.worldui.utils.Utils;

import java.io.IOException;

@RestController
public class MainController {

    @RequestMapping(method = RequestMethod.GET, path = "/", produces = "application/json")
    public String index2() throws IOException {


        String test = "";
        try {
            String strObj = Utils.webParser("http://localhost:8080");
            ResponseObject response = (ResponseObject) Utils.fromString(strObj);
            return response.getCityList().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Something went wrong";
    }


}