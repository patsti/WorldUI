package patrik.worldui.objects;

import java.util.ArrayList;
import java.util.List;

public class MenuNavigation {
    List<MenuNavigationObject> menu;

    /**
     * This object is used to keep track of the navigation-bar
     *
     * @param currentPage Name of the current page.
     */
    public MenuNavigation(String currentPage) {
        menu = new ArrayList<>();
        menu.add(new MenuNavigationObject("Home", currentPage.equals("Home")));
        menu.add(new MenuNavigationObject("Game", currentPage.equals("Game")));
        menu.add(new MenuNavigationObject("About", currentPage.equals("About")));
        menu.add(new MenuNavigationObject("DBlist", currentPage.equals("Country")
                || currentPage.equals("Languages")
                || currentPage.equals("City")));
        menu.add(new MenuNavigationObject("Contact", currentPage.equals("Contact")));
    }

    public List<MenuNavigationObject> getMenu() {
        return menu;
    }
}
