package patrik.restapi.objects;

import java.io.Serializable;

public abstract class CompareAbstract implements Serializable {
    private final static long serialVersionUID = 1337;
    String countrycode;


    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
}
