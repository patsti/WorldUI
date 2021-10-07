package patrik.worldui.objects;

import java.util.*;

/**
 * A helper class to navigate a game session.
 */
public class NavigationHelper {
    public String selected;
    public List<String> selectedList = new ArrayList<>();
    List<String> singleSelected = new ArrayList<>();
    private Integer seed = 0;
    private String gameSession;

    public NavigationHelper(String selected, Integer seed, String gameSession) {
        if (seed != null) {
            this.seed = seed;
        }
        this.gameSession = gameSession;
        if (selected != null && !selected.isEmpty()) {
            this.selected = selected;
            Collections.addAll(selectedList, selected.split(","));

            Set<String> uniqueGas = new HashSet<>(selectedList);
            for (String str : uniqueGas) {
                int occurances = Collections.frequency(selectedList, str);
                if (occurances == 1) {
                    singleSelected.add(str);
                }
            }
            this.selected = singleSelected.toString().replace("[", "").replace("]", "").replaceAll(" ", "");
            while (!this.selected.matches("[0-9]*(\\,[0-9])*")) {
                this.selected = this.selected.substring(1);
            }
        } else {
            this.selected = "";
        }
    }

    /**
     * @param list   list of all currentGuesses
     * @param addStr New Guess
     * @return list of current guesses without duplicates.
     */
    private String removeDuplicates(List<String> list, String addStr) {
        list.add(addStr);
        Set<String> uniqueGas = new HashSet<>(list);
        List<String> singleSelected = new ArrayList<>();
        for (String str : uniqueGas) {
            int occurrences = Collections.frequency(list, str);
            if (occurrences == 1) {
                singleSelected.add(str);
            }
        }
        String selected = singleSelected.toString().replace("[", "").replace("]", "").replaceAll(" ", "");
        while (!selected.matches("[0-9]*(\\,[0-9])*")) {
            selected = selected.substring(1);
        }
        return selected;
    }

    /**
     * @return true if game is ready to be validated (2 guesses selected).
     */
    public boolean isTwoAnswerSelected() {
        return singleSelected.size() >= 2;
    }

    /**
     * @param str id of guess-box.
     * @return css-style for (un)selected guesses
     */
    public String getCss(String str) {
        if (singleSelected.contains(str)) {
            return "child_div_selected";
        }
        return "child_div";
    }

    /**
     * @param indexOfBox index of which box was clicked
     * @return url-parameters used to navigate the app.
     */
    public String onGuessClicked(String indexOfBox) {
        List<String> checkedAnswers = new ArrayList<>(singleSelected);
        if (seed == 0) {
            return removeDuplicates(checkedAnswers, indexOfBox) + "&gamesession=" + gameSession + "&newselect=" + indexOfBox;
        }
        return removeDuplicates(checkedAnswers, indexOfBox) + "&seed=" + seed + "&gamesession=" + gameSession + "&newselect=" + indexOfBox;
    }

    @Override
    public String toString() {
        return selected;
    }

}
