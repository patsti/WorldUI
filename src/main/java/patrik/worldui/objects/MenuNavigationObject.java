package patrik.worldui.objects;

public class MenuNavigationObject {
    String name;
    boolean isActive;

    MenuNavigationObject(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    /**
     * @return Css style, to visualize which is the current page.
     */
    public String isActiveCss() {
        return isActive ? " active" : "";
    }

    /**
     * @return Name to be visible in navigation bar
     */
    public String getName() {
        return name;
    }

    /**
     * @return html page name, used for navigation
     */
    public String getHtmlName() {
        return name.toLowerCase();
    }
}
