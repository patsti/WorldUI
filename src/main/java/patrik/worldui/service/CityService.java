package patrik.worldui.service;

import org.springframework.stereotype.Service;
import patrik.guiutils.Page;
import patrik.guiutils.Paged;
import patrik.guiutils.Paging;
import patrik.restapi.objects.City;
import patrik.restapi.objects.Country;
import patrik.restapi.objects.CountryLanguage;
import patrik.restapi.objects.ResponseObject;
import patrik.worldui.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    public Paged getCities(int pageNumber, int size, String countrycode) {
        try {
            String res = "";
            if (countrycode != null && !countrycode.isEmpty()) {
                res = Utils.webParser("http://localhost:8080/api/city?countrycode="+countrycode);
            } else {
                res = Utils.webParser("http://localhost:8080");
            }
            ResponseObject responseObject = (ResponseObject) Utils.fromString(res);
            List<City> cityList = responseObject.getCityList();
            int skip = pageNumber > 1 ? (pageNumber - 1) * size : 0;
            List<City> paged = cityList.stream()
                    .skip(skip)
                    .limit(size)
                    .collect(Collectors.toList());

            int totalPages = ( (cityList.size() - 1 ) / size ) +1 ;
            return new Paged(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Paged<>(new Page<>(null, 0), Paging.of(0, pageNumber, size));
    }
    
    @SuppressWarnings({"all"})
    public Paged getCountries(int pageNumber, int size, String countrycode) {
        try {
            String res = Utils.webParser("http://localhost:8080/api/countries?countrycode="+countrycode);
            ResponseObject responseObject = (ResponseObject) Utils.fromString(res);
            assert responseObject != null;
            List<Country> countryList = responseObject.getCountryList();
            int skip = pageNumber > 1 ? (pageNumber - 1) * size : 0;
            List<Country> paged = countryList.stream()
                    .skip(skip)
                    .limit(size)
                    .collect(Collectors.toList());

            int totalPages = ( (countryList.size() - 1 ) / size ) +1 ;
            return new Paged(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Paged<>(new Page<>(null, 0), Paging.of(0, pageNumber, size));
    }

    public Paged getCountryLanguages(int pageNumber, int size, String countrycode) {
        try {
            String res = Utils.webParser("http://localhost:8080/api/countrylanguages?countrycode="+countrycode);

            ResponseObject responseObject = (ResponseObject) Utils.fromString(res);
            assert responseObject != null;
            List<CountryLanguage> cityList = responseObject.getCountryLanguageList();
            int skip = pageNumber > 1 ? (pageNumber - 1) * size : 0;
            List<CountryLanguage> paged = cityList.stream()
                    .skip(skip)
                    .limit(size)
                    .collect(Collectors.toList());

            int totalPages = ( (cityList.size() - 1 ) / size ) +1 ;
            return new Paged(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Paged<>(new Page<>(null, 0), Paging.of(0, pageNumber, size));
    }
}
