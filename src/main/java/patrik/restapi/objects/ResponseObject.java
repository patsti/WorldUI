package patrik.restapi.objects;

import java.io.Serializable;
import java.util.List;

public class ResponseObject implements Serializable {
    private final static long serialVersionUID = 1337;
    List<City> cityList;
    List<Country> countryList;
    List<CountryLanguage> countryLanguageList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<CountryLanguage> getCountryLanguageList() {
        return countryLanguageList;
    }

    public void setCountryLanguageList(List<CountryLanguage> countryLanguageList) {
        this.countryLanguageList = countryLanguageList;
    }
}
