package patrik.worldui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import patrik.restapi.objects.City;
import patrik.restapi.objects.Country;
import patrik.restapi.objects.GameResponseObject;
import patrik.worldui.NavigationHelper;
import patrik.worldui.service.GameService;
import patrik.worldui.utils.Utils;

import java.util.Random;

@Controller
public class GameController {


    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/game")
    @GetMapping
    public String game(Model model,
                       @RequestParam(value = "gamesession", required = false, defaultValue = "") String gamesession,
                       @RequestParam(value = "selected", required = false, defaultValue = "") String selected,
                       @RequestParam(value = "seed", required = false, defaultValue = "0") Integer seed,
                       @RequestParam(value = "result", required = false, defaultValue = "") String result) {

        GameResponseObject gameResponseObject = null;
        if (!gamesession.isEmpty()) {
            gameResponseObject = (GameResponseObject) Utils.deserialize(gamesession);
        }
        if (gameResponseObject == null) {
            gameResponseObject = gameService.initGame(seed);
        }
        City city1 = null;
        City city2 = null;
        Country country1 = null;
        Country country2 = null;
        for (Object obj : gameResponseObject.getItems()) {
            if (obj instanceof City) {
                if (city1 == null) {
                    city1 = (City) obj;
                } else {
                    city2 = (City) obj;
                }
            } else if (obj instanceof Country) {
                if (country1 == null) {
                    country1 = (Country) obj;
                } else {
                    country2 = (Country) obj;
                }
            }
        }

        NavigationHelper navigationHelper = new NavigationHelper(selected, seed, Utils.serialize(gameResponseObject));

        model.addAttribute("navigation", navigationHelper);
        model.addAttribute("city1", city1);
        model.addAttribute("city2", city2);
        model.addAttribute("country1", country1);
        model.addAttribute("country2", country2);
        if (!result.isEmpty()) {
            model.addAttribute("animation", result.equals("WIN") ? "correct_answer" : "wrong_answer");
        } else {
            model.addAttribute("animation", "anim");
        }

        return "game";
    }

    @GetMapping("/gameredir")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model,
                                                        @RequestParam(value = "gamesession", required = false, defaultValue = "") String gamesession,
                                                        @RequestParam(value = "newselect", required = false, defaultValue = "-1") Integer newselected,
                                                        @RequestParam(value = "selected", required = false, defaultValue = "") String selected,
                                                        @RequestParam(value = "seed", required = false, defaultValue = "") Integer seed) {
        if (newselected != -1) {
            GameResponseObject gameResponseObject = (GameResponseObject) Utils.deserialize(gamesession);
            gameResponseObject.addGuess(gameResponseObject.getItems().get(newselected - 1));
            NavigationHelper navigationHelper = new NavigationHelper(selected, seed, Utils.serialize(gameResponseObject));
            model.addAttribute("gamesession", Utils.serialize(gameResponseObject));
            model.addAttribute("selected", selected);
            model.addAttribute("seed", seed);
            String redirect = "redirect:/";
            redirect += navigationHelper.isTwoAnswerSelected() ? "result" : "game";
            return new ModelAndView(redirect, model);
        }
        return new ModelAndView("redirect:/game-start", model);
    }

    @GetMapping("/game-start")
    public ModelAndView startNewGame(ModelMap model,
                                     @RequestParam(value = "result", required = false, defaultValue = "") String result) {
        Random random = new Random();
        int random_seed = random.nextInt(10000);
        if (!result.isEmpty()) {
            model.addAttribute("result", result);
        }
        model.addAttribute("seed", random_seed);
        String redirect = "redirect:/game";
        return new ModelAndView(redirect, model);
    }

    @RequestMapping(value = "/result")
    public ModelAndView verifyResult(ModelMap model,
                                     @RequestParam(value = "gamesession", required = false, defaultValue = "") String gamesession,
                                     @RequestParam(value = "selected", required = false, defaultValue = "") String selected,
                                     @RequestParam(value = "seed", required = false, defaultValue = "") Integer seed) {

        GameResponseObject gameResponseObject = (GameResponseObject) Utils.deserialize(gamesession);
        NavigationHelper navigationHelper = new NavigationHelper(selected, seed, gamesession);
        model.addAttribute("navigation", navigationHelper);

        if (gameResponseObject != null && gameResponseObject.isCorrectGuess()) {
            System.out.println("Correct answer");
            model.addAttribute("result", "WIN");
            return new ModelAndView("redirect:/game-start", model);
        } else {
            System.out.println("Wrong answer");
            model.addAttribute("result", "LOOSE");
            return new ModelAndView("redirect:/game-start", model);
        }
    }

}