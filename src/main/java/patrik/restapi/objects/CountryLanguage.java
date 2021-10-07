package patrik.restapi.objects;

import java.io.Serializable;

public class CountryLanguage extends CompareAbstract implements Serializable {
    String isofficial;
    Float percentage;

    public String getIsofficial() {
        return isofficial;
    }

    public void setIsofficial(String isofficial) {
        this.isofficial = isofficial;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}
