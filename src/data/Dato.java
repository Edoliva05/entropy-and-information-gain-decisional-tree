package data;
import java.util.Map;

public class Dato {
    private final Map<String, Boolean> features;

    // Costruttore che accetta una mappa di feature (es. "Piove" -> true, "Vento" -> false)
    public Dato(Map<String, Boolean> features){
        this.features = features;
    }

    // Metodo chiamato da InternalNode per capire che strada prendere
    public boolean getFeatureValue(String attributeName){
        return features.getOrDefault(attributeName, false);
    }
}
