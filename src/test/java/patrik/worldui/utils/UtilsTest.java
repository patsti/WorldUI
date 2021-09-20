package patrik.worldui.utils;

import junit.framework.TestCase;
import patrik.restapi.objects.City;
import patrik.restapi.objects.Country;
import patrik.restapi.objects.CountryLanguage;

public class UtilsTest extends TestCase {
    City city;
    Country country;
    CountryLanguage countryLanguage;

    public void setUp() throws Exception {
        super.setUp();
        city = new City();
        city.setId(1337);
        city.setDistrict("disctrict");
        city.setName("CityName");
        city.setCountrycode("countrycode");
        city.setPopulation(1337);

        country = new Country();
        country.setName("CountryName");

        countryLanguage = new CountryLanguage();
        countryLanguage.setLanguage("Language");
    }

    public void testSerialize() {
        String serializedCity = Utils.serialize(city);
        City deserializedCity = (City) Utils.deserialize(serializedCity);

        String serializedCountry = Utils.serialize(country);
        Country deserializedCountry = (Country) Utils.deserialize(serializedCountry);

        String serializedLanguage = Utils.serialize(countryLanguage);
        CountryLanguage deserializedLanguage = (CountryLanguage) Utils.deserialize(serializedLanguage);

        assertEquals(city.getName(), deserializedCity.getName());
        assertEquals(country.getName(), deserializedCountry.getName());
        assertEquals(countryLanguage.getLanguage(), deserializedLanguage.getLanguage());

    }

}