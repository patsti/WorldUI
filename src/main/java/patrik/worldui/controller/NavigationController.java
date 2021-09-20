package patrik.worldui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import patrik.worldui.service.CityService;

import java.util.Random;

@Controller
public class NavigationController {


    private final CityService cityService;

    @Autowired
    public NavigationController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/cities")
    public String list(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                       @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model,
                       @RequestParam(value = "countrycode", required = false, defaultValue = "") String countrycode) {
        model.addAttribute("cities", cityService.getCities(pageNumber, size, countrycode));

        return "cities";
    }

    @RequestMapping(value = "/country")
    public String countrylist(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                       @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model,
                       @RequestParam(value = "countrycode", required = false, defaultValue = "") String countrycode) {
        model.addAttribute("countries", cityService.getCountries(pageNumber, size, countrycode));

        return "country";
    }

    @RequestMapping(value = "/languages")
    public String countrylanguagelist(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                       @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model,
                       @RequestParam(value = "countrycode", required = false, defaultValue = "") String countrycode) {
        model.addAttribute("languages", cityService.getCountryLanguages(pageNumber, size, countrycode));

        return "languages";
    }

    @GetMapping("/dblist")
    public ModelAndView startNewGame(ModelMap model) {
        String redirect = "redirect:/cities";
        return new ModelAndView(redirect, model);
    }

    @RequestMapping(value = "/home")
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "/contact")
    public String contactPage() {
        return "contact";
    }

    @RequestMapping(value = "/about")
    public String aboutPage() {
        return "about";
    }

}