package patrik.restapi.objects;

import java.io.Serializable;

public class City extends CompareAbstract implements Serializable  {
    Integer id;
    private String district;
    private Integer population;

    public int getId() {
        return id;
    }
    public String getDistrict() {
        return district;
    }
    public int getPopulation() {
        return population;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString(){
        return name;
    }

}
