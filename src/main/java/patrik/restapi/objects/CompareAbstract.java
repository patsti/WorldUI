package patrik.restapi.objects;

import java.io.Serializable;

public abstract class CompareAbstract implements Serializable {
    private final static long serialVersionUID = 1337;
    String countrycode;
    String name;

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
