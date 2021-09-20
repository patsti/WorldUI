package patrik.worldui;

import java.util.*;

public class NavigationHelper {
    public String selected = "";
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

            Set<String> uniqueGas = new HashSet<String>(selectedList);
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

    private String removeDuplicates(List<String> list, String addStr) {
        list.add(addStr);
        Set<String> uniqueGas = new HashSet<String>(list);
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

    public boolean isTwoAnswerSelected() {
        return singleSelected.size() >= 2;
    }

    public String getCss(String str) {
        if (singleSelected.contains(str)) {
            return "child_div_selected";
        }
        return "child_div";
    }

    public String getClick1() {
        List<String> checkedAnswers = new ArrayList<>(singleSelected);
        if (seed == 0) {
            return removeDuplicates(checkedAnswers, "1") + "&gamesession=" + gameSession + "&newselect=" + 1;
        }
        return removeDuplicates(checkedAnswers, "1") + "&seed=" + seed + "&gamesession=" + gameSession + "&newselect=" + 1;
    }

    public String getClick2() {
        List<String> checkedAnswers = new ArrayList<>(singleSelected);
        if (seed == 0) {
            return removeDuplicates(checkedAnswers, "2") + "&gamesession=" + gameSession + "&newselect=" + 2;
        }
        return removeDuplicates(checkedAnswers, "2") + "&seed=" + seed + "&gamesession=" + gameSession + "&newselect=" + 2;
    }

    public String getClick3() {
        List<String> checkedAnswers = new ArrayList<>(singleSelected);
        if (seed == 0) {
            return removeDuplicates(checkedAnswers, "3") + "&gamesession=" + gameSession + "&newselect=" + 3;
        }
        return removeDuplicates(checkedAnswers, "3") + "&seed=" + seed + "&gamesession=" + gameSession + "&newselect=" + 3;
    }

    public String getClick4() {
        List<String> checkedAnswers = new ArrayList<>(singleSelected);
        if (seed == 0) {
            return removeDuplicates(checkedAnswers, "4") + "&gamesession=" + gameSession + "&newselect=" + 4;
        }
        return removeDuplicates(checkedAnswers, "4") + "&seed=" + seed + "&gamesession=" + gameSession + "&newselect=" + 4;
    }

    @Override
    public String toString() {
        return selected;
    }

}
