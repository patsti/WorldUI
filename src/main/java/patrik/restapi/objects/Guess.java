package patrik.restapi.objects;

import java.io.Serializable;

public class Guess implements Serializable {
    private final static long serialVersionUID = 1337;
    CompareAbstract item1;
    CompareAbstract item2;

    boolean isCorrect() {
        if (item1 != null && item2 != null) {
            return item1.countrycode.equals(item2.countrycode);
        }
        return false;
    }
}
