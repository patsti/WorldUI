package patrik.worldui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import patrik.worldui.objects.MenuNavigation;
import patrik.worldui.service.CityService;

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
        model.addAttribute("menuItems", new MenuNavigation("Cities"));
        model.addAttribute("cities", cityService.getCities(pageNumber, size, countrycode));
        if (!countrycode.isEmpty()) {
            model.addAttribute("countrycode", "&countrycode=" + countrycode);
        } else {
            model.addAttribute("countrycode", "");
        }

        return "cities :: navigationBar(page='navigationbar', fragment='navigationbar')";
    }

    @RequestMapping(value = "/country")
    public String countrylist(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                              @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model,
                              @RequestParam(value = "countrycode", required = false, defaultValue = "") String countrycode) {
        model.addAttribute("menuItems", new MenuNavigation("Country"));
        model.addAttribute("countries", cityService.getCountries(pageNumber, size, countrycode));
        if (!countrycode.isEmpty()) {
            model.addAttribute("countrycode", "&countrycode=" + countrycode);
        } else {
            model.addAttribute("countrycode", "");
        }

        return "country :: navigationBar(page='navigationbar', fragment='navigationbar')";
    }

    @RequestMapping(value = "/languages")
    public String countrylanguagelist(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                      @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model,
                                      @RequestParam(value = "countrycode", required = false, defaultValue = "") String countrycode) {
        model.addAttribute("menuItems", new MenuNavigation("Languages"));
        model.addAttribute("languages", cityService.getCountryLanguages(pageNumber, size, countrycode));
        if (!countrycode.isEmpty()) {
            model.addAttribute("countrycode", "&countrycode=" + countrycode);
        } else {
            model.addAttribute("countrycode", "");
        }
        return "languages :: navigationBar(page='navigationbar', fragment='navigationbar')";
    }

    @RequestMapping("/dblist")
    public ModelAndView redirectToDbTable(ModelMap model) {
        String redirect = "redirect:/cities";
        return new ModelAndView(redirect, model);
    }

    @RequestMapping(value = "/")
    public ModelAndView homePageRedirect(ModelMap model) {
        String redirect = "redirect:/home";
        return new ModelAndView(redirect, model);
    }

    @RequestMapping(value = "/home")
    public String homePage(ModelMap model) {
        model.addAttribute("menuItems", new MenuNavigation("Home"));
        return "home :: navigationBar(page='navigationbar', fragment='navigationbar')";
    }

    @RequestMapping(value = "/contact")
    public String contactPage(ModelMap model) {
        model.addAttribute("menuItems", new MenuNavigation("Contact"));
        return "contact :: navigationBar(page='navigationbar', fragment='navigationbar')";
    }

    @RequestMapping(value = "/about")
    public String aboutPage(ModelMap model) {
        model.addAttribute("menuItems", new MenuNavigation("About"));
        return "about :: navigationBar(page='navigationbar', fragment='navigationbar')";
    }

}