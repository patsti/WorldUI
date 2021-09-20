package patrik.restapi.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameResponseObject implements Serializable {
    private final static long serialVersionUID = 1337;
    List<CompareAbstract> gameItems;
    Guess guess;

    public void addItem(CompareAbstract city) {
        if (gameItems == null) {
            gameItems = new ArrayList<>();
        }
        gameItems.add(city);
    }

    public List<CompareAbstract> getItems() {
        return gameItems;
    }

    public void addGuess(CompareAbstract item) {
        if (guess != null){
            guess.item2 = item;
        } else {
            guess = new Guess();
            guess.item1 = item;
        }
    }

    public boolean isCorrectGuess() {
        return guess != null && guess.isCorrect();
    }

    public void clearGuess() {
        guess = null;
    }
}
